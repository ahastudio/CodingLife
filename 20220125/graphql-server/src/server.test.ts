import { gql } from 'apollo-server-core';

import { createApolloServer } from './server';

const server = createApolloServer();

test('query messages', async () => {
  const result = await server.executeOperation({
    query: gql`
      query GetMessages {
        messages {
          id
          name
          body
        }
      }
    `,
  });

  expect(result.data).toEqual({
    messages: [],
  });
});

test('mutation createMessage', async () => {
  const result = await server.executeOperation({
    query: gql`
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

  expect(result.data).toEqual({
    createMessage: {
      id: '1',
      name: 'Tester',
      body: 'Hello, world!',
    },
  });
});

test('subscription messageAdded', async () => {
  const result = await server.executeOperation({
    query: gql`
      subscription MessageAdded {
        messageAdded {
          id
          name
          body
        }
      }
    `,
  });

  expect(result.errors).toBeUndefined();
});
