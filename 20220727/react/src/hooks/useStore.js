import { useEffect } from 'react';

import useForceUpdate from './useForceUpdate';

export default function useStore(store) {
  const forceUpdate = useForceUpdate();

  useEffect(() => {
    store.subscribe(forceUpdate);

    return () => store.unsubscribe(forceUpdate);
  }, []);

  return store;
}
