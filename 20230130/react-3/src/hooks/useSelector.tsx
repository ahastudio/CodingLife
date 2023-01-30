import { container } from 'tsyringe';

import { useEffect, useRef } from 'react';

import useForceUpdate from './useForceUpdate';

import Store, { State } from '../stores/Store';

type Selector<T> = (state: State) => T;

export default function useSelector<T>(selector: Selector<T>): T {
  const store = container.resolve(Store);

  const state = useRef(selector(store.state));

  const forceUpdate = useForceUpdate();

  useEffect(() => {
    const update = () => {
      const newState = selector(store.state);
      // selector의 결과가 객체인 경우 처리 필요함.
      if (newState !== state.current) {
        forceUpdate();
        state.current = newState;
      }
    };

    store.addListener(update);

    return () => store.removeListener(update);
  }, [store, forceUpdate]);

  return selector(store.state);
}
