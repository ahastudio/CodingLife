import { useEffect } from 'react';

import useProductDetailStore from './useProductDetailStore';
import useProductFormStore from './useProductFormStore';

import { ProductDetail } from '../types';

export default function useFetchProduct({ productId }: {
  productId: string;
}): {
  product: ProductDetail;
  loading: boolean;
  error: boolean;
} {
  const [{
    product, loading, error,
  }, productDetailStore] = useProductDetailStore();

  const [, productFormStore] = useProductFormStore();

  useEffect(() => {
    productDetailStore.fetchProduct({ productId });
  }, [productDetailStore, productId]);

  useEffect(() => {
    if (!loading && !error) {
      productFormStore.setProduct(product);
    }
  }, [loading, error, product]);

  return { product, loading, error };
}
