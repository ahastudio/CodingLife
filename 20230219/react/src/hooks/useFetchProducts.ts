import { container } from 'tsyringe';

import { useStore } from 'usestore-ts';

import { useEffect } from 'react';

import ProductsStore from '../stores/ProductsStore';

import { ProductSummary } from '../types';

export default function useFetchProducts({ categoryId }: {
  categoryId?: string;
}): {
  products: ProductSummary[];
} {
  const store = container.resolve(ProductsStore);

  const [{ products }] = useStore<ProductsStore>(store);

  useEffect(() => {
    store.fetchProducts({ categoryId });
  }, [store, categoryId]);

  return { products };
}
