import React from 'react';

export default function Counter({ count, onClick }) {
  return (
    <button type="button" onClick={() => onClick(1)}>
      Click me!
      (
      { count }
      )
    </button>
  );
}
