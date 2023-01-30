import { container } from 'tsyringe';

import { Action } from '../stores/BaseStore';

import Store from '../stores/Store';

export default function useDispatch<T>() {
  const store = container.resolve(Store);
  return (action: Action<T>) => store.dispatch(action);
}
