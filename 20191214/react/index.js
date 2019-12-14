import React from 'react';
import ReactDOM from 'react-dom';

document.body.innerHTML += '<div id="app"></div>';

function App() {
  return (
    <>
      <h1>Welcome</h1>
      <p>Hello, world!</p>
    </>
  );
}

ReactDOM.render(React.createElement(App), document.getElementById('app'));
