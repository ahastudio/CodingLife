import useCounterStore from '../hooks/useCounterStore';
import useCartStore from '../hooks/useCartStore';

export default function Buttons() {
  const [, counterStore] = useCounterStore();
  const [, cartStore] = useCartStore();

  const handleClickIncrease = () => {
    counterStore.increase();
  };

  const handleClickAddProduct = () => {
    cartStore.addItem();
  };

  return (
    <div>
      <button type="button" onClick={handleClickIncrease}>
        Increase
      </button>
      {' '}
      <button type="button" onClick={handleClickAddProduct}>
        Add product
      </button>
    </div>
  );
}
