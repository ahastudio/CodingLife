import { useEffect } from 'react';

import usePubSub from '../hooks/usePubSub';

export default function Output() {
  const { subscribe, unsubscribe, value } = usePubSub();

  useEffect(() => {
    subscribe('count');
    return () => unsubscribe('count');
  }, []);

  const count = value('count');

  return (
    <div>
      <p>
        Count:
        {' '}
        {String(count)}
      </p>
      <p>
        Time:
        {' '}
        {new Date().getTime()}
      </p>
    </div>
  );
}
