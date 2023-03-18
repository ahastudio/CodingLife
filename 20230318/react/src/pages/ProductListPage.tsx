import Products from '../components/Products';

import useFetchProducts from '../hooks/useFetchProducts';

export default function ProductListPage() {
  const products = useFetchProducts();

  return (
    <div>
      <h2>Product List</h2>
      <Products products={products} />
    </div>
  );
}
