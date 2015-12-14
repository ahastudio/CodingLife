import React from 'react';
import ReactDOM from 'react-dom';
import { Router, Route, Link } from 'react-router';

class Navigation extends React.Component {
  render() {
    return (
      <nav>
        <Link to="/">Home</Link>
        |
        <Link to="/users">Users</Link>
      </nav>
    );
  }
}

class Hello extends React.Component {
  render() {
    return (
      <div>
        <Navigation />
        <h1>Hello, world!</h1>
      </div>
    );
  }
}

class Users extends React.Component {
  state = {
    users: [1, 2, 3],
  }
  render() {
    return (
      <div>
        <Navigation />
        <h1>Users</h1>
        {this.state.users.map((user, index) =>
          <p key={index}><Link to={`/users/${user}`}>User #{user}</Link></p>
        )}
      </div>
    );
  }
}

class UserDetail extends React.Component {
  state = {
    user: {}
  }
  componentDidMount() {
    this.setState({
      user: {
        id: this.props.params.id
      }
    });
  }
  render() {
    return (
      <div>
        <Navigation />
        <h1>User</h1>
        {this.state.user.id}
      </div>
    );
  }
}

ReactDOM.render((
  <Router>
    <Route path="/" component={Hello} />
    <Route path="/users" component={Users} />
    <Route path="/users/:id" component={UserDetail} />
  </Router>
), document.getElementById('app'));
