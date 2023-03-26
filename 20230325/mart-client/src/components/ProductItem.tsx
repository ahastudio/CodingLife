import useAddProductToCart from '../hooks/useAddProductToCart';

import numberFormat from '../utils/numberFormat';

import { Product } from '../types';

type ProductItemProps = {
  product: Product;
}

export default function ProductItem({ product }: ProductItemProps) {
  const addProductToCart = useAddProductToCart();

  const handleClick = async () => {
    await addProductToCart(product);
    window.dispatchEvent(new Event('reload-cart'));
  };

  return (
    <li>
      <div>{product.name}</div>
      <div>
        {numberFormat(product.price)}
        원
      </div>
      <button type="button" onClick={handleClick}>카드 담기</button>
    </li>
  );
}
