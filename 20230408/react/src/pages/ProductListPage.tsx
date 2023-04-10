import { useSearchParams } from 'react-router-dom';

import Products from '../components/product-list/Products';

import useFetchProducts from '../hooks/useFetchProducts';

export default function ProductListPage() {
  const [params] = useSearchParams();

  const categoryId = params.get('categoryId') ?? undefined;

  const { products } = useFetchProducts({ categoryId });

  return (
    <div>
      <h2>Products</h2>
      <Products products={products} />
    </div>
  );
}
