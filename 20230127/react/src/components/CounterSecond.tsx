import useCounterStore from '../hooks/useCounterStore';

export default function CounterSecond() {
  const counterStore = useCounterStore();

  const { count } = counterStore;

  return (
    <div>
      <p>{count}</p>
      <p>
        <button
          type="button"
          onClick={() => counterStore.increase()}
        >
          Increase
        </button>
        <button
          type="button"
          onClick={() => counterStore.increase(10)}
        >
          Jump
        </button>
        <button
          type="button"
          onClick={() => counterStore.decrease()}
        >
          Decrease
        </button>
      </p>
    </div>
  );
}
