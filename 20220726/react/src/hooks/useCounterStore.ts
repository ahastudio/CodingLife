import { useStore } from 'usestore-ts';

import CounterStore from '../stores/CounterStore';

export const counterStore = new CounterStore();

export default function useCounterStore() {
  return useStore(counterStore);
}
