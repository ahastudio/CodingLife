import ProductItem from './ProductItem';

import useFetchProducts from '../hooks/useFetchProducts';

export default function ProductList() {
  const { products, loading, error } = useFetchProducts();

  if (error) {
    return (
      <p>Error!</p>
    );
  }

  if (loading) {
    return (
      <p>Loading...</p>
    );
  }

  return (
    <div style={{ flex: 1 }}>
      <h2>Product List</h2>
      <ul>
        {products.map((product) => (
          <ProductItem
            key={product.id}
            product={product}
          />
        ))}
      </ul>
    </div>
  );
}
