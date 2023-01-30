import useForceUpdate from '../hooks/useForceUpdate';

// Business Logic

const state = {
  count: 0,
};

function increase() {
  state.count += 1;
}

// UI

export default function Counter() {
  const forceUpdate = useForceUpdate();

  const handleClick = () => {
    increase();
    forceUpdate();
  };

  return (
    <div>
      <p>{state.count}</p>
      <button type="button" onClick={handleClick}>
        Increase
      </button>
    </div>
  );
}
