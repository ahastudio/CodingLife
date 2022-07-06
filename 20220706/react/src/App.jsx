import { useState } from 'react';

import Greeting from './Greeting';
import InputName from './InputName';
import ToDoApp from './ToDoApp';

export default function App() {
  const [name, setName] = useState('');

  const handleChange = (event) => {
    const { value } = event.target;
    setName(value);
  };

  return (
    <div>
      <Greeting name={name || 'world'} />
      <InputName name={name} onChange={handleChange} />
      <hr />
      <ToDoApp />
    </div>
  );
}
