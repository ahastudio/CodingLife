import useCounterStore from '../hooks/useCounterStore';

export default function Counter() {
  const [{ count }] = useCounterStore();

  return (
    <div>
      Count:
      {' '}
      {count}
    </div>
  );
}
