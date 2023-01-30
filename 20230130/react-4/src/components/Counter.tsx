import useCounterStore from '../hooks/useCounterStore';

export default function Counter() {
  const store = useCounterStore();

  const { count } = store;

  return (
    <div>
      <p>
        Count:
        {' '}
        {count}
      </p>
    </div>
  );
}
