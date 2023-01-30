import { singleton } from 'tsyringe';

const initialState = {
  count: 0,
  name: 'Tester',
};

export type State = typeof initialState;

export type Action<T> = {
  type: string;
  payload?: T;
}

function defaultReducer(state: State, action: Action<unknown>): State {
  switch (action.type) {
  case 'increase':
    return {
      ...state,
      count: state.count + ((action as Action<number>).payload ?? 1),
    };
  case 'decrease':
    return {
      ...state,
      count: state.count - 1,
    };
  default:
    return state;
  }
}

type Reducer = <T>(state: State, action: Action<T>) => State;

type Listener = () => void;

@singleton()
export default class Store {
  state: State = initialState;

  reducer: Reducer = defaultReducer;

  listeners = new Set<Listener>();

  dispatch<T>(action: Action<T>) {
    this.state = this.reducer(this.state, action);
    this.listeners.forEach((listener) => listener());
  }

  addListener(listener: Listener) {
    this.listeners.add(listener);
  }

  removeListener(listener: Listener) {
    this.listeners.delete(listener);
  }
}
