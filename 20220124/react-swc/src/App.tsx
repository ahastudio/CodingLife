import { useState } from 'react';

import Input from './components/Input';
import Output from './components/Output';
import Counter from './components/Counter';

export default function App() {
  const [flag, setFlag] = useState(true);

  return (
    <div>
      <h1>Hello, world!</h1>
      {flag && (
        <>
          <Input />
          <Output />
          <Counter />
        </>
      )}
      <button type="button" onClick={() => setFlag(!flag)}>
        Toggle
      </button>
    </div>
  );
}
