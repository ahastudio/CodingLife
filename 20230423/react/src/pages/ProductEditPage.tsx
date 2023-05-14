import { useEffect } from 'react';

import { useNavigate, useParams } from 'react-router-dom';

import ProductEditForm from '../components/product/ProductEditForm';

import useFetchProduct from '../hooks/useFetchProduct';
import useFetchCategories from '../hooks/useFetchCategories';
import useProductFormStore from '../hooks/useProductFormStore';

export default function ProductEditPage() {
  const navigate = useNavigate();

  const params = useParams();
  const productId = String(params.id);

  const { product, refresh } = useFetchProduct({ productId });
  const { categories } = useFetchCategories();

  const [, store] = useProductFormStore();

  useEffect(() => {
    if (!product) {
      return;
    }

    store.setProduct(product);
  }, [store, product]);

  const handleComplete = () => {
    refresh();
    navigate(`/products/${productId}`);
  };

  if (!product || !categories.length) {
    return null;
  }

  return (
    <ProductEditForm
      categories={categories}
      onComplete={handleComplete}
    />
  );
}
