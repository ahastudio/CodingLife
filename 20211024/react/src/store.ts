/* eslint-disable import/prefer-default-export */

import createStore from 'teaful';

const initialState = {
  name: '',
  count: 0,
};

// TODO: Remove `undefined`. It's bug of Teaful's `index.d.ts` file.
export const { useStore } = createStore(initialState, undefined);
