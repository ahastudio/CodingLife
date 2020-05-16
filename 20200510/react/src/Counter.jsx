import React from 'react';

export default function Counter({ count, onClick }) {
  return (
    <button type="button" onClick={onClick}>
      Click me!
      (
      { count }
      )
    </button>
  );
}
