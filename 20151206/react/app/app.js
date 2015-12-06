import React from 'react';
import ReactDOM from 'react-dom';

class Hello extends React.Component {
  render() {
    return (
      <div>
        <h1>Hello</h1>
        <p>Hello, world!</p>
      </div>
    );
  }
}

ReactDOM.render(<Hello />, document.getElementById('app'));
