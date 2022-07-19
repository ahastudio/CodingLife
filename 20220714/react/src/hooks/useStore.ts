import { useSyncExternalStore } from 'react';

import { STORE_GLUE_PROPERTY_NAME, StoreGlue } from '../stores/core';

import log from '../utils/log';

export default function useStore<T extends object>(store: T): T {
  const glue: StoreGlue = Reflect.get(store, STORE_GLUE_PROPERTY_NAME);
  if (!glue) {
    throw new Error('Cannot find store glue');
  }
  const snapshot = useSyncExternalStore(
    glue.subscribe.bind(glue),
    glue.getSnapshot.bind(glue),
  );
  log('* useStore - snapshot:', JSON.stringify(snapshot));
  return store;
}
