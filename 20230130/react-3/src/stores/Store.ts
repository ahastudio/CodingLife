import { singleton } from 'tsyringe';

import BaseStore, { Action } from './BaseStore';

// type State = {
//   count: number;
// }

const initialState = {
  count: 0,
  name: 'Tester',
};

export type State = typeof initialState;

const reducers = {
  increase(state: State, action: Action<number>) {
    return {
      ...state,
      count: state.count + (action.payload ?? 1),
    };
  },

  decrease(state: State, action: Action<number>) {
    return {
      ...state,
      count: state.count - (action.payload ?? 1),
    };
  },
};

export function increase(step?: number) {
  return { type: 'increase', payload: step };
}

export function decrease(step?: number) {
  return { type: 'decrease', payload: step };
}

@singleton()
export default class Store extends BaseStore<State> {
  constructor() {
    super(initialState, reducers);
  }
}
