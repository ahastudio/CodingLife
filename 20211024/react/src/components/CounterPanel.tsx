import { useStore } from '../store';

export default function CounterPanel() {
  const [counter] = useStore.counter();

  return (
    <p>
      Count:
      {' '}
      {counter.count()}
    </p>
  );
}
