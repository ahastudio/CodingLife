import { useStore } from '../store';

export default function CounterPanel() {
  const [count] = useStore.count();

  return (
    <p>
      Count:
      {' '}
      {count}
    </p>
  );
}
