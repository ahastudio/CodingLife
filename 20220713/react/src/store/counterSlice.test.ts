import counterReducer, {
  initialState,
  increment,
  decrement,
  incrementByAmount,
} from './counterSlice';

test('increment', () => {
  const state = counterReducer(initialState, increment());

  expect(state.value).toBe(1);
});

test('decrement', () => {
  const state = counterReducer(initialState, decrement());

  expect(state.value).toBe(-1);
});

test('incrementByAmount', () => {
  const state = counterReducer(initialState, incrementByAmount(5));

  expect(state.value).toBe(5);
});
