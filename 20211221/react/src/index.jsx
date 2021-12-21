import ReactDOM from 'react-dom';

import { Provider } from 'react-redux';

import { setupStore } from './store';

import App from './App';

const store = setupStore();

const rootElement = document.getElementById('app');

ReactDOM.render(
  (
    <Provider store={store}>
      <App />
    </Provider>
  ),
  rootElement,
);
