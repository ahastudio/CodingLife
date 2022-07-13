import { useAppDispatch, useAppSelector } from '.';

import { decrement, increment, incrementByAmount } from '../store/counterSlice';

export default function useCounter() {
  const dispatch = useAppDispatch();

  const count = useAppSelector((state) => state.counter.value);

  return {
    count,
    increase: () => dispatch(increment()),
    decrease: () => dispatch(decrement()),
    increase10: () => dispatch(incrementByAmount(10)),
    decrease10: () => dispatch(incrementByAmount(-10)),
  };
}
