import { useEffect, useState } from 'react';

import useCreateUserImmediately from '../hooks/useCreateUserImmediately';

export default function Greeting() {
  console.log('render Greeting!');

  // useCreateUserImmediately({ name: 'Mr.Hong', job: 'Tester' });

  useEffect(() => {
    // Invalid hook call!
    // useState(0);
  }, []);

  const handleClick = () => {
    // Invalid hook call!
    // useState(0);
    useCreateUserImmediately({ name: 'Mr.Hong', job: 'Tester' });
  };

  return (
    <div>
      Hello, world!
      &nbsp;&nbsp;
      <button type="button" onClick={handleClick}>
        👋
      </button>
    </div>
  );
}
