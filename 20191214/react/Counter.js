import React from 'react';

import { useSelector, useDispatch } from 'react-redux';

export default function Counter() {
  const count = useSelector(state => state.count);

  const dispatch = useDispatch();

  const handleClick = () => dispatch({ type: 'increase' });

  return (
    <>
      <p>Count: {count}</p>
      <p>
        <button type="button" onClick={handleClick}>
          Click
        </button>
      </p>
    </>
  );
}
