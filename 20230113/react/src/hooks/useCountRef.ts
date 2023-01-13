import { useEffect, useRef } from 'react';

export default function useCountRef() {
  const count = useRef(0);

  useEffect(() => {
    console.log('Effect!');

    setTimeout(() => {
      console.log(`Timeout: ${count.current}`);
    }, 5_000);
  }, []);

  return count;
}
