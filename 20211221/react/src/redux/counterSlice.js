import { createSlice } from '@reduxjs/toolkit';

export const { actions, reducer } = createSlice({
  name: 'counter',
  initialState: {
    count: 0,
  },
  reducers: {
    increment: (state) => ({
      ...state,
      count: state.count + 1,
    }),
    decrement: (state) => ({
      ...state,
      count: state.count - 1,
    }),
    incrementByAmount: (state, { payload: { amount } }) => ({
      ...state,
      count: state.count + amount,
    }),
  },
});

export const {
  increment,
  decrement,
  incrementByAmount,
} = actions;

export default reducer;
