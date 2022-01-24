import { useEffect, useRef } from 'react';

import usePubSub from '../hooks/usePubSub';

type SubCounterProps = {
  step: number;
}

function SubCounter({ step }: SubCounterProps) {
  const count = useRef<number>(0);

  const { subscribe, unsubscribe } = usePubSub();

  useEffect(() => {
    subscribe('count');
    return () => unsubscribe('count');
  }, []);

  count.current += step;

  return (
    <p>
      {count.current}
    </p>
  );
}

export default function Counter() {
  const count = useRef<number>(0);

  const { subscribe, unsubscribe } = usePubSub();

  useEffect(() => {
    subscribe('count');
    return () => unsubscribe('count');
  }, []);

  count.current += 1;

  return (
    <div>
      <SubCounter step={1} />
      {count.current <= 5 && (
        <SubCounter step={2} />
      )}
    </div>
  );
}
