import { useSyncExternalStore } from 'react';

import { STORE_GLUE_PROPERTY_NAME } from '../stores/core';

type FunctionPropertyNames<T> = {
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  [K in keyof T]: T[K] extends (...args: any) => any ? K : never
}[keyof T];

type NonFunctionProperties<T> = Omit<T, FunctionPropertyNames<T>>;

export default function useStore<Store extends object>(
  store: Store,
): [NonFunctionProperties<Store>, Readonly<Store>] {
  const glue = Reflect.get(store, STORE_GLUE_PROPERTY_NAME);
  if (!glue) {
    throw new Error('Cannot find store glue');
  }
  const snapshot = useSyncExternalStore(
    glue.subscribe.bind(glue),
    glue.getSnapshot.bind(glue),
  ) as NonFunctionProperties<Store>;
  return [snapshot, store];
}
