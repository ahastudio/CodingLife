import { container } from 'tsyringe';

import { useStore } from 'usestore-ts';

import CartStore from '../stores/CartStore';

const cartStore = container.resolve(CartStore);

export default function useCartStore() {
  return useStore(cartStore);
}
