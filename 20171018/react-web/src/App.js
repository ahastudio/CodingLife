import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      name: '',
    };
  }

  render() {
    const { name } = this.state;
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Welcome to React</h1>
        </header>
        <p className="App-intro">
          Hello, {name || 'world'}!
        </p>
        <p>
          <input type="text"
                 placeholder="Input your name"
                 value={name}
                 onChange={e => this.setState({ name: e.target.value })} />
        </p>
      </div>
    );
  }
}

export default App;
