import { container } from 'tsyringe';

import { useStore } from 'usestore-ts';

import CounterStoreTS from '../stores/CounterStoreTS';

function useCounterStore() {
  const store = container.resolve(CounterStoreTS);
  return useStore(store);
}

export default function CounterThird() {
  const [{ count }, counterStore] = useCounterStore();

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
