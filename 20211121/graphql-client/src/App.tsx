import React from 'react';

import { createClient, Provider } from 'urql';

import Tasks from './components/Tasks';

const client = createClient({
  url: 'http://localhost:3000/graphql',
});

export default function App() {
  return (
    <React.StrictMode>
      <Provider value={client}>
        <Tasks />
      </Provider>
    </React.StrictMode>
  );
}
