import React from 'react';
import ReactDOM from 'react-dom/client';

import App from './App';

function getName() {
  const query = window.location.search.substring(1);
  const params = new URLSearchParams(query);
  return params.get('name');
}

function main() {
  const element = document.getElementById('root');

  if (!element) {
    return;
  }

  const root = ReactDOM.createRoot(element);

  root.render((
    React.createElement(
      React.StrictMode,
      {},
      React.createElement(App, {
        name: getName() || 'world',
      }),
    )
  ));
}

main();
