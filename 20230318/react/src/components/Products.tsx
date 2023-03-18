import { Link } from 'react-router-dom';

import ProductModel from '../models/Product';

type ProductsProps = {
  products: ProductModel[];
};

export default function Products({ products }: ProductsProps) {
  return (
    <ul>
      {products.map((product) => (
        <li key={product.id}>
          <Link to={`/products/${product.id}`}>
            {product.name}
          </Link>
        </li>
      ))}
    </ul>
  );
}
