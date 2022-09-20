import { useState } from 'react';

export default function useForceUpdate() {
  const [count, setCount] = useState(0);

  return () => {
    setCount(count + 1);
  };
}
