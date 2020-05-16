import React, { useState } from 'react';

import Page from './Page';

export default function App() {
  const [state, setState] = useState({
    count: 0,
  });

  const { count } = state;

  function handleClick() {
    setState({
      ...state,
      count: count + 1,
    });
  }

  function handleChange(value) {
    setState({
      ...state,
      count: value,
    });
  }

  return (
    <Page
      count={count}
      onClick={handleClick}
      onChange={handleChange}
    />
  );
}
