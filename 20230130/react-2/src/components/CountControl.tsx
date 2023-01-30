import useCounterStore from '../hooks/useCounterStore';

export default function CountControl() {
  const store = useCounterStore();

  const handleClickIncrease = () => {
    store.increase();
  };

  const handleClickDecrease = () => {
    store.decrease();
  };

  return (
    <div>
      <p>{store.count}</p>
      <button type="button" onClick={handleClickIncrease}>
        Increase
      </button>
      <button type="button" onClick={handleClickDecrease}>
        Decrease
      </button>
    </div>
  );
}
