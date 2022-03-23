import React, { Component } from 'react';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import { Helmet } from 'react-helmet';

const Home = () => (
  <div className="home">
    <Helmet>
      <title>Demo - Welcome!</title>
      <meta name="description" content="WELCOME" />
    </Helmet>
    <p>Welcome!</p>
  </div>
);

const About = () => (
  <div className="about">
    <Helmet>
      <title>Demo - About</title>
    </Helmet>
    <p>ABOUT...</p>
  </div>
);

class App extends Component {
  render() {
    return (
      <BrowserRouter>
        <div className="App">
          <Link to="/">Home</Link>
          {' '}
          |
          {' '}
          <Link to="/about">About</Link>
          <hr />
          <Routes>
            <Route path="/" element={<Home />}/>
            <Route path="/about" element={<About />}/>
          </Routes>
        </div>
      </BrowserRouter>
    );
  }
}

export default App;
