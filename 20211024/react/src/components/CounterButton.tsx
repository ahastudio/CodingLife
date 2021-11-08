import { useStore } from '../store';

export default function CounterButton() {
  const [counter, setCounter] = useStore.counter();

  const handleClick = () => {
    setCounter(counter.increase());
  };

  return (
    <div>
      <button type="button" onClick={handleClick}>
        Click me!
      </button>
    </div>
  );
}
