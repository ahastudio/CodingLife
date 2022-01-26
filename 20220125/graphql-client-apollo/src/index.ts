import { ApolloClient, gql } from '@apollo/client/core';

import { createApolloClient } from './client';

function subscribeMessageAdded(client: ApolloClient<any>) {
  return new Promise((resolve, reject) => {
    client.subscribe({
      query: gql`
        subscription MessageAdded {
          messageAdded {
            id
            name
            body
          }
        }
      `,
    }).subscribe({
      next: resolve,
      error: reject,
    });
  });
}

async function createMessage(client: ApolloClient<any>) {
  const result = await client.mutate({
    mutation: gql`
      mutation CreateMessage($name: String!, $body: String!) {
        createMessage(name: $name, body: $body) {
          id
          name
          body
        }
      }
    `,
    variables: {
      name: 'Tester',
      body: 'Hello, world!',
    },
  });

  console.log('\n===[mutation createMessage]===');
  console.log(result);
}

async function main() {
  const client1 = createApolloClient({ port: 3000, path: '/graphql' });
  const client2 = createApolloClient({ port: 3000, path: '/graphql' });

  const promise = subscribeMessageAdded(client1);

  await new Promise((resolve) => setTimeout(resolve, 100));

  createMessage(client2);

  const result = await promise;
  console.log('\n===[subscription messageAdded]===');
  console.log(result);
}

main();
