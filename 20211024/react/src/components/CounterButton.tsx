import { useStore } from '../store';

export default function CounterButton() {
  const [count, setCount] = useStore.count();

  const handleClick = () => {
    setCount(count + 1);
  };

  return (
    <div>
      <button type="button" onClick={handleClick}>
        Click me!
      </button>
    </div>
  );
}
