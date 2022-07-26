import useCartStore from '../hooks/useCartStore';

function numberFormat(value: number) {
  return new Intl.NumberFormat().format(value);
}

export default function Cart() {
  const [{ cart }] = useCartStore();

  const { price, items } = cart;

  return (
    <div>
      <p>
        Total price:
        {' '}
        {numberFormat(price)}
        원
      </p>
      {!!items.length && (
        <ul>
          {items.map((item) => (
            <li key={item.id}>
              {item.title}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}
