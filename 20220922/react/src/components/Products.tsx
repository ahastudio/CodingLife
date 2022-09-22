import useCartStore from '../hooks/useCartStore';

export default function Products() {
  // TODO: CartStore 얻기
  const [, cartStore] = useCartStore();

  const addItem = (productId: number) => {
    // dispatch(addItem({ productId, quantity: 1 }))
    cartStore.addItem({ productId, quantity: 1 });
  };

  return (
    <div>
      <h2>Products</h2>
      <ul>
        {[1, 2, 3, 4, 5].map((id) => (
          <li key={id}>
            Product #
            {id}
            {' '}
            <button type="button" onClick={() => addItem(id)}>
              Add to cart
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}
