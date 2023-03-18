import { Link, useParams } from 'react-router-dom';

import useFetchProduct from '../hooks/useFetchProduct';

export default function ProductDetailPage() {
  const { id } = useParams();

  const product = useFetchProduct(id);

  if (!product) {
    return null;
  }

  return (
    <div>
      <h2>
        Product:
        {' '}
        {product.name}
      </h2>
      <Link to="/cart">장바구니</Link>
    </div>
  );
}
