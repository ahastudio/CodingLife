import { useEffect } from 'react';

import useForceUpdate from './useForceUpdate';

import { bankStore } from '../stores/BankStore';

export default function useBankStore() {
  const forceUpdate = useForceUpdate();

  useEffect(() => {
    bankStore.subscribe(forceUpdate);

    return () => bankStore.unsubscribe(forceUpdate);
  }, [forceUpdate]);

  return bankStore;
}
