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
  const container = document.getElementById('app');
  if (!container) {
    // TODO: 에러 표시
    return;
  }

  const root = ReactDOM.createRoot(container);
  root.render(<Root />);
}

main();
