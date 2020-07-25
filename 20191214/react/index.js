import React from 'react';
import ReactDOM from 'react-dom';

import { createStore } from 'redux'
import { Provider } from 'react-redux';

import reducer from './reducer';

import Counter from './Counter';

document.body.innerHTML += '<div id="app"></div>';

function App() {
  return (
    <>
      <h1>Welcome</h1>
      <p>Hello, world!</p>
      <hr />
      <Counter />
    </>
  );
}

const store = createStore(reducer);

ReactDOM.render((
  <Provider store={store}>
    <App />
  </Provider>
), document.getElementById('app'));
