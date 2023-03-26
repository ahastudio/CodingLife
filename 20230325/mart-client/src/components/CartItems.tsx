import CartItem from './CartItem';

import { CartItem as CartItemType } from '../types';

type CartItemsProps = {
  cartItems: CartItemType[];
}

export default function CartItems({ cartItems }: CartItemsProps) {
  if (!cartItems.length) {
    return (
      <p>No Item</p>
    );
  }

  return (
    <div>
      <ul>
        {cartItems.map((cartItem) => (
          <CartItem
            key={cartItem.id}
            cartItem={cartItem}
          />
        ))}
      </ul>
    </div>
  );
}
