import { createClient, defaultExchanges, subscriptionExchange } from '@urql/core';
import { SubscriptionClient } from 'subscriptions-transport-ws';
import ws from 'ws';
import 'isomorphic-unfetch';

import { pipe, subscribe } from 'wonka';

const MESSAGE_ADDED = `
  subscription MessageAdded {
    messageAdded {
      id
      name
      body
    }
  }
`;

const CREATE_MESSAGE = `
  mutation CreateMessage($name: String!, $body: String!) {
    createMessage(name: $name, body: $body) {
      id
      name
      body
    }
  }
`;

function makeClient() {
  const subscriptionClient = new SubscriptionClient(
    'ws://localhost:3000/graphql',
    { reconnect: true },
    ws,
  );

  const client = createClient({
    url: 'http://localhost:3000/graphql',
    exchanges: [
      ...defaultExchanges,
      subscriptionExchange({
        forwardSubscription(operation) {
          return subscriptionClient.request(operation);
        },
      }),
    ],
  });

  return client;
}

function subscribeMessageAdded(client) {
  const { unsubscribe } = pipe(
    client.subscription(MESSAGE_ADDED),
    subscribe((result) => {
      const { data, error } = result;
      console.log('\n===[subscription messageAdded]===');
      console.log('DATA:', data);
      console.log('ERROR:', error);
      // unsubscribe();
    }),
  );
}

function createMessage(client, { name, body }) {
  const { unsubscribe } = pipe(
    client.mutation(CREATE_MESSAGE, { name: 'TESTER', body: 'HI!' }),
    subscribe((result) => {
      const { data, error } = result;
      console.log('\n===[mutation createMessage]===');
      console.log('DATA:', data);
      console.log('ERROR:', error);
      // unsubscribe();
    }),
  );
}

async function main() {
  const client1 = makeClient();
  const client2 = makeClient();

  subscribeMessageAdded(client1);

  await new Promise((resolve) => setTimeout(resolve, 100));

  createMessage(client2, { name: 'TESTER', body: 'HI!' });
}

main();
