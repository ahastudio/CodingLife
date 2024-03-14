import { useState } from 'react';

export default function App() {
  const [count, setCount] = useState(0);

  const handleClick = () => {
    setCount((count) => count + 1);
  };

  return (
    <>
      <h1>Vite + React</h1>
      <div>
        <button onClick={handleClick}>
          Counter: {count}
        </button>
      </div>
    </>
  )
}
