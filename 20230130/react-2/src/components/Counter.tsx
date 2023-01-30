import useCounterStore from '../hooks/useCounterStore';

export default function Counter() {
  const store = useCounterStore();

  return (
    <div>
      <p>
        Count:
        {' '}
        {store.count}
      </p>
    </div>
  );
}
