import http from 'http';

import { SubscriptionServer } from 'subscriptions-transport-ws';
import { ApolloClient, gql } from '@apollo/client/core';

import { startServer } from '../src/server';

import { createApolloClient, closeApolloClient } from './client';

const port = 3333;
const path = '/graphql';

let httpServer: http.Server;

beforeAll(async () => {
  const server = await startServer({ port });
  httpServer = server.httpServer;
});

afterAll(() => {
  httpServer.close();
});

function subscribe(client: ApolloClient<any>, query: any): Promise<any> {
  return new Promise((resolve, reject) => {
    client
      .subscribe({ query })
      .subscribe({
        next: resolve,
        error: reject,
      });
  });
}

test('subscription messageAdded', async () => {
  const client1 = createApolloClient({ port, path });
  const client2 = createApolloClient({ port, path });

  const promise = subscribe(client1, gql`
    subscription MessageAdded {
      messageAdded {
        id
        name
        body
      }
    }
  `);

  await new Promise((resolve) => {
    setTimeout(resolve, 100);
  });

  const result = await client2.mutate({
    mutation: gql`
      mutation CreateMessage {
        createMessage(name: "Tester", body: "Hello, world!") {
          id
          name
          body
        }
      }
    `,
  });

  const { createMessage: message } = result.data;

  expect(message.body).toBe('Hello, world!');

  const { data } = await promise;

  expect(data.messageAdded).toEqual(message);

  closeApolloClient(client1);
  closeApolloClient(client2);
}, 10_000);
