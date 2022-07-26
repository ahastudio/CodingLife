import { container } from 'tsyringe';

import { useStore } from 'usestore-ts';

import CounterStore from '../stores/CounterStore';

const counterStore = container.resolve(CounterStore);

export default function useCounterStore() {
  return useStore(counterStore);
}
