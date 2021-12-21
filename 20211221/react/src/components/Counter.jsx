import { useDispatch, useSelector } from 'react-redux';

import { increment, decrement, incrementByAmount } from '../redux/counterSlice';

export default function Counter() {
  const dispatch = useDispatch();
  const counter = useSelector((state) => state.counter);
  const { count } = counter;

  return (
    <div>
      {count}
      {' '}
      <button type="button" onClick={() => dispatch(increment())}>
        [+]
      </button>
      {' '}
      <button type="button" onClick={() => dispatch(decrement())}>
        [-]
      </button>
      {' '}
      <button
        type="button"
        onClick={() => dispatch(incrementByAmount({ amount: 2 }))}
      >
        [+2]
      </button>
    </div>
  );
}
