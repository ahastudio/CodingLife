import { container } from 'tsyringe';

import { useStore } from 'usestore-ts';

import OrderFormStore from '../stores/OrderFormStore';

export default function useOrderFormStore() {
  const store = container.resolve(OrderFormStore);
  return useStore(store);
}
