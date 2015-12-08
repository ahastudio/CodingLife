import React from 'react';
import ReactDOM from 'react-dom';

class Hello extends React.Component {
  state = {
    items: ['world', 'the Earth'],
    text: ''
  }
  onChange = (e) => {
    this.setState({text: e.target.value});
  }
  onSubmit = () => {
    if (this.state.text) {
      this.setState({
        items: this.state.items.concat(this.state.text),
        text: ''
      });
    }
  }
  render() {
    return (
      <div>
        <h1>Hello</h1>
        <h2>{this.state.text}</h2>
        {this.state.items.map((item, index) =>
          <p key={index}>Hello, {item}!</p>
        )}
        <div>
          <input onChange={this.onChange} value={this.state.text} />
          <button onClick={this.onSubmit}>Add</button>
        </div>
      </div>
    );
  }
}

ReactDOM.render(<Hello />, document.getElementById('app'));
