import { useSyncExternalStore } from 'react';

import { STORE_PROPERTY_NAME, ExternalStore } from '../stores/core';

export default function useStore(store: any) {
  const externalStore: ExternalStore = Reflect.get(store, STORE_PROPERTY_NAME);
  if (!store) {
    throw new Error('Cannot find store');
  }
  return useSyncExternalStore(
    externalStore.subscribe.bind(externalStore),
    externalStore.getSnapshot.bind(externalStore),
  );
}
