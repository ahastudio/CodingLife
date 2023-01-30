import { container } from 'tsyringe';

import Store, { Action } from '../stores/Store';

export default function useDispatch<T>() {
  const store = container.resolve(Store);
  return (action: Action<T>) => store.dispatch(action);
}
