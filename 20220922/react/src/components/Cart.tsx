import useCartStore from '../hooks/useCartStore';

export default function Cart() {
  // TODO: CartStore의 snapshot 얻기
  const [snapshot] = useCartStore();

  // const items = useSelector((state) => state.cart.items)
  const { items } = snapshot;

  return (
    <div>
      <h2>Cart</h2>
      {items.length ? (
        <ul>
          {items.map((item) => (
            <li key={item.id}>
              Product #
              {item.productId}
              {' - '}
              {item.quantity}
            </li>
          ))}
        </ul>
      ) : (
        <p>Empty</p>
      )}
    </div>
  );
}
