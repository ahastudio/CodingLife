import { CartItem as CartItemType } from '../types';

import numberFormat from '../utils/numberFormat';

type CartItemProps = {
  cartItem: CartItemType;
}

export default function CartItem({ cartItem }: CartItemProps) {
  const { product } = cartItem;
  return (
    <li>
      <div>{product.name}</div>
      <div>
        단가:
        {numberFormat(product.price)}
        원
      </div>
      <div>
        수량:
        {numberFormat(cartItem.quantity)}
      </div>
      <div>
        총:
        {numberFormat(product.price * cartItem.quantity)}
        원
      </div>
    </li>
  );
}
