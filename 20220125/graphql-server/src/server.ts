import http from 'http';
import express from 'express';

import { ApolloServer } from 'apollo-server-express';
import { ApolloServerPluginDrainHttpServer, gql } from 'apollo-server-core';

import { PubSub } from 'graphql-subscriptions';
import { SubscriptionServer } from 'subscriptions-transport-ws';
import { makeExecutableSchema } from '@graphql-tools/schema';
import { execute, subscribe } from 'graphql';

const pubsub = new PubSub();

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

class Message {
  id: string;

  name: string;

  body: string;

  constructor({ id, name, body }: {
    id: string;
    name: string;
    body: string;
  }) {
    this.id = id;
    this.name = name;
    this.body = body;
  }
}

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

let newId = 0;
const messages: Message[] = [];

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

const typeDefs = gql`
  type Message {
    id: ID!
    name: String!
    body: String!
  }

  type Query {
    messages: [Message!]!
  }

  type Mutation {
    createMessage(name: String!, body: String!): Message
  }

  type Subscription {
    messageAdded: Message
  }
`;

const resolvers = {
  Query: {
    messages(): Message[] {
      return messages;
    },
  },
  Mutation: {
    createMessage(_: any, { name, body }: {
      name: string;
      body: string;
    }): Message {
      newId += 1;

      const message = new Message({ id: String(newId), name, body });
      messages.push(message);

      pubsub.publish('MESSAGE_ADDED', {
        messageAdded: message,
      });

      return message;
    },
  },
  Subscription: {
    messageAdded: {
      subscribe: () => pubsub.asyncIterator(['MESSAGE_ADDED']),
    },
  },
};

const schema = makeExecutableSchema({ typeDefs, resolvers });

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

export function createApolloServer({ httpServer }: {
  httpServer?: http.Server;
} = {}): ApolloServer {
  return new ApolloServer({
    typeDefs,
    resolvers,
    plugins: httpServer
      ? [ApolloServerPluginDrainHttpServer({ httpServer })]
      : [],
  });
}

export function createSubscriptionServer({ httpServer, path }: {
  httpServer: http.Server;
  path: string;
}): SubscriptionServer {
  return new SubscriptionServer({
    execute,
    subscribe,
    schema,
  }, {
    server: httpServer,
    path,
  });
}

export async function startServer({ app = express(), port }: {
  app?: express.Application,
  port: number;
}) {
  const httpServer = http.createServer(app);

  const apolloServer = createApolloServer({ httpServer });
  await apolloServer.start();
  apolloServer.applyMiddleware({ app });

  const { graphqlPath: path } = apolloServer;

  createSubscriptionServer({ httpServer, path });

  await httpServer.listen({ port });

  return { httpServer, path };
}
