// import { useState } from 'react';

import { useBoolean } from 'usehooks-ts';

export default function Switch() {
  const { value: flag, toggle: toggleFlag } = useBoolean();

  const handleClick = () => {
    toggleFlag();

    // Invalid hook call!
    // useState(0);
  };

  return (
    <p>
      {flag ? '⭕️' : '❌'}
      &nbsp;&nbsp;
      <button type="button" onClick={handleClick}>
        Toggle
      </button>
    </p>
  );
}
