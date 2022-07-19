import { createSlice } from '@reduxjs/toolkit';

type CounterState = {
  value: number;
}

export const initialState: CounterState = {
  value: 0,
};

const counterSlice = createSlice({
  name: 'counter',
  initialState,
  reducers: {
    increment: (state) => ({
      ...state,
      value: state.value + 1,
    }),
    decrement: (state) => ({
      ...state,
      value: state.value - 1,
    }),
    incrementByAmount: (state, action) => ({
      ...state,
      value: state.value + action.payload,
    }),
  },
});

export const {
  increment,
  decrement,
  incrementByAmount,
} = counterSlice.actions;

export default counterSlice.reducer;
