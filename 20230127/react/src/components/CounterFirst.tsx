import useDispatch from '../hooks/useDispatch';
import useSelector from '../hooks/useSelector';

import { State } from '../stores/Store';

function increase(step: number | undefined = undefined) {
  return {
    type: 'increase',
    payload: step,
  };
}

function decrease() {
  return {
    type: 'decrease',
  };
}

function selector(state: State) {
  return state.count;
}

export default function CounterFirst() {
  const dispatch = useDispatch();

  const count = useSelector(selector);

  return (
    <div>
      <p>{count}</p>
      <p>
        <button
          type="button"
          onClick={() => dispatch(increase())}
        >
          Increase
        </button>
        <button
          type="button"
          onClick={() => dispatch(increase(10))}
        >
          Jump
        </button>
        <button
          type="button"
          onClick={() => dispatch(decrease())}
        >
          Decrease
        </button>
      </p>
    </div>
  );
}
