import { useEffect } from 'react';

export default function Greeting() {
  useEffect(() => {
    // Invalid hook call!
    // useState(0);
  }, []);

  return (
    <div>
      Hello, world!
    </div>
  );
}
