import { useSyncExternalStore } from 'react';

import { STORE_PROPERTY_NAME, ExternalStore } from '../stores/core';

export default function useStore(store: any) {
  const externalStore: ExternalStore = Reflect.get(store, STORE_PROPERTY_NAME);
  if (!externalStore) {
    throw new Error('Cannot find external store');
  }
  return useSyncExternalStore(
    externalStore.subscribe.bind(externalStore),
    externalStore.getSnapshot.bind(externalStore),
  );
}
