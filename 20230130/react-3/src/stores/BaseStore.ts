/* eslint-disable @typescript-eslint/no-explicit-any */

export type Action<Payload> = {
  type: string;
  payload?: Payload;
}

type Reducer<State, Payload> = (state: State, action: Action<Payload>) => State;

type Reducers<State> = Record<string, Reducer<State, any>>;

type Listener = () => void;

export default class BaseStore<State> {
  state: State;

  reducer: Reducer<State, any>;

  listeners = new Set<Listener>();

  constructor(initialState: State, reducers: Reducers<State>) {
    this.state = initialState;

    this.reducer = (state: State, action: Action<any>) => {
      const reducer = Reflect.get(reducers, action.type);
      if (!reducer) {
        return state;
      }
      return reducer(state, action);
    };
  }

  dispatch(action: Action<any>) {
    this.state = this.reducer(this.state, action);
    this.publish();
  }

  publish() {
    this.listeners.forEach((listener) => {
      listener();
    });
  }

  addListener(listener: Listener) {
    this.listeners.add(listener);
  }

  removeListener(listener: Listener) {
    this.listeners.delete(listener);
  }
}
