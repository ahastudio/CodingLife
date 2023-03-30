import { useEffect } from 'react';

import { container } from 'tsyringe';

import { useStore } from 'usestore-ts';

import ProductsStore from '../stores/ProductsStore';

export default function useFetchProducts({ categoryId }: {
  categoryId?: string;
}) {
  const store = container.resolve(ProductsStore);

  const [{ products }] = useStore(store);

  useEffect(() => {
    store.fetchProducts({ categoryId });
  }, [store, categoryId]);

  return { products };
}
