import { container } from 'tsyringe';

import { useStore } from 'usestore-ts';

import { useEffectOnce } from 'usehooks-ts';

import CartStore from '../stores/CartStore';

export default function useFetchCart() {
  const store = container.resolve(CartStore);

  const [{ cart }] = useStore<CartStore>(store);

  useEffectOnce(() => {
    store.fetchCart();
  });

  return { cart };
}
