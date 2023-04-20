import { container } from 'tsyringe';

import { useEffect } from 'react';

import { useStore } from 'usestore-ts';

import OrderDetailStore from '../stores/OrderDetailStore';

export default function useFetchOrder({ orderId }: {
  orderId: string;
}) {
  const store = container.resolve(OrderDetailStore);

  const [{ order, loading, error }] = useStore(store);

  useEffect(() => {
    store.fetchOrder({ orderId });
  }, [store]);

  return { order, loading, error };
}
