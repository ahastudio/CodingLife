import createStore from 'fragstore';

const initialState = {
  name: '',
  count: 0,
};

export const { Store, useStore } = createStore(initialState);
