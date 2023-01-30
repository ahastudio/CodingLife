import { container } from 'tsyringe';

import { useEffect } from 'react';

import useForceUpdate from './useForceUpdate';

import CounterStore from '../stores/CounterStore';

export default function useCounterStore() {
  const forceUpdate = useForceUpdate();

  const store = container.resolve(CounterStore);

  useEffect(() => {
    store.addListener(forceUpdate);
    return () => store.removeListener(forceUpdate);
  }, [store]);

  return store;
}
