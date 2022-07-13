/* eslint-disable import/prefer-default-export */

import { ReactNode } from 'react';

import { Provider } from 'react-redux';

import store from './store';

export function StoreProvider({ children }: {
  children: ReactNode;
}) {
  return (
    <Provider store={store}>
      {children}
    </Provider>
  );
}
