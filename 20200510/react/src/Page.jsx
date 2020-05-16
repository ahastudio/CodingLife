import React from 'react';

import Greeting from './Greeting';
import Counter from './Counter';
import Buttons from './Buttons';

export default function Page({ count, onClick, onChange }) {
  return (
    <div>
      <Greeting />
      <Counter
        count={count}
        onClick={onClick}
      />
      <Buttons
        onClick={onChange}
      />
    </div>
  );
}
