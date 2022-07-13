import useCounter from '../hooks/useCounter';

export default function Counter() {
  const {
    count, increase, decrease, increase10, decrease10,
  } = useCounter();

  return (
    <div>
      <p>
        Count:
        {' '}
        {count}
      </p>
      <p>
        <button type="button" onClick={increase}>+</button>
        {' '}
        <button type="button" onClick={decrease}>-</button>
        {' '}
        <button type="button" onClick={increase10}>+10</button>
        {' '}
        <button type="button" onClick={decrease10}>-10</button>
      </p>
    </div>
  );
}
