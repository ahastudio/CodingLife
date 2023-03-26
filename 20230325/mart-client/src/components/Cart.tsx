import CartItems from './CartItems';

import useFetchCart from '../hooks/useFetchCart';

import numberFormat from '../utils/numberFormat';

export default function Cart() {
  const { cart, loading, error } = useFetchCart();

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
      <h2>Cart</h2>
      <CartItems cartItems={cart.cartItems} />
      <div>
        합계:
        {' '}
        {numberFormat(cart.totalPrice)}
        원
      </div>
    </div>
  );
}
