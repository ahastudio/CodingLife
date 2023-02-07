import React from 'react';

import Greeting from './components/Greeting';

type AppProps = {
  name: string;
}

export default function App({ name }:AppProps) {
  return (
    React.createElement(
      'div',
      {},
      React.createElement(Greeting, { name }),
    )
  );
}
