import { useStore } from 'usestore-ts';

import CartStore from '../stores/CartStore';

export const cartStore = new CartStore();

export default function useCartStore() {
  return useStore(cartStore);
}
