import { useMicroState } from '@hya/micro-state';

const { log } = console;

export default function Counter() {
  log('<Counter>');

  const [count, setCount] = useMicroState('count', 0);

  const handleClickIncrease = () => {
    setCount(count + 1);
  };

  const handleClickDecrease = () => {
    setCount(count - 1);
  };

  return (
    <div>
      <p>
        Count:
        {' '}
        {count}
      </p>
      <p>
        <button type="button" onClick={handleClickIncrease}>
          +1
        </button>
        <button type="button" onClick={handleClickDecrease}>
          -1
        </button>
      </p>
    </div>
  );
}
