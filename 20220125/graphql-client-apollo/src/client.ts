/* eslint-disable import/no-extraneous-dependencies */

import fetch from 'cross-fetch';
import WebSocket from 'ws';

import {
  ApolloClient, InMemoryCache, HttpLink, split,
} from '@apollo/client/core';
import { WebSocketLink } from '@apollo/client/link/ws';
import { getMainDefinition } from '@apollo/client/utilities';

export function createApolloClient({ port, path }: {
  port: number;
  path: string;
}) {
  const httpLink = new HttpLink({
    uri: `http://localhost:${port}${path}`,
    fetch,
  });

  const wsLink = new WebSocketLink({
    uri: `ws://localhost:${port}${path}`,
    options: {
      reconnect: true,
    },
    webSocketImpl: WebSocket,
  });

  const splitLink = split(
    ({ query }) => {
      const definition = getMainDefinition(query);
      return definition.kind === 'OperationDefinition'
                            && definition.operation === 'subscription';
    },
    wsLink,
    httpLink,
  );

  return Object.assign(
    (
      new ApolloClient({
        link: splitLink,
        cache: new InMemoryCache(),
      })
    ),
    {
      wsLink,
    },
  );
}

export function closeApolloClient(apolloClient: any) {
  (apolloClient.wsLink as any).subscriptionClient.close();
}
