import { container } from 'tsyringe';

import { useStore } from 'usestore-ts';

import CounterStore from '../stores/CounterStore';

export default function useCounterStore() {
  const counterStore = container.resolve(CounterStore);
  return useStore(counterStore);
}
