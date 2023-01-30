import useDispatch from '../hooks/useDispatch';

function increase() {
  return { type: 'increase' };
}

export default function Counter() {
  const dispatch = useDispatch();

  return (
    <div>
      <button
        type="button"
        onClick={() => dispatch(increase())}
      >
        Press
      </button>
    </div>
  );
}
