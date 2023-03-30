import CartView from '../components/cart/CartView';

import useFetchCart from '../hooks/useFetchCart';

export default function OrderPage() {
  const { cart } = useFetchCart();

  if (!cart) {
    return null;
  }

  return (
    <div>
      <h2>장바구니</h2>
      <CartView cart={cart} />
    </div>
  );
}
