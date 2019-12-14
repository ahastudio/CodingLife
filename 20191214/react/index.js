import React from 'react';
import ReactDOM from 'react-dom';

document.body.innerHTML += '<div id="app"></div>';

function App() {
  return React.createElement(React.Fragment, null,
    React.createElement('h1', null, 'Welcome'),
    React.createElement('p', null, 'Hello, world!'),
  );
}

ReactDOM.render(React.createElement(App), document.getElementById('app'));
