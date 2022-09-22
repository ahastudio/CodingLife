import React from 'react';
import ReactDOM from 'react-dom/client';

import App from './App';

function Root() {
  return (
    <React.StrictMode>
      <App />
    </React.StrictMode>
  );
}

function main() {
  const container = document.getElementById('root');
  if (!container) {
    return;
  }

  const root = ReactDOM.createRoot(container);
  root.render(<Root />);
}

main();
