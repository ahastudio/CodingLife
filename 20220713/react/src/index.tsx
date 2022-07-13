import ReactDOM from 'react-dom/client';

import { Provider } from 'react-redux';

import App from './App';

import store from './store';

function main() {
  const container = document.getElementById('app');
  if (!container) {
    return;
  }

  const root = ReactDOM.createRoot(container);
  root.render((
    <Provider store={store}>
      <App />
    </Provider>
  ));
}

main();
