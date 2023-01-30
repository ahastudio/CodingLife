import React from 'react';
import ReactDOM from 'react-dom/client';

import App from './App';

function main() {
  const element = document.getElementById('root');

  if (!element) {
    return;
  }

  const root = ReactDOM.createRoot(element);

  root.render((
    <React.StrictMode>
      <App />
    </React.StrictMode>
  ));
}

main();
