const MAX_COUNT = 100;
const COUNT_FACTOR = 5;

const initialState = {
  count: 0,
};

const reducers = {
  increase(state, action) {
    const { count } = state;

    if (count >= MAX_COUNT) {
      return state;
    }

    return { ...state, count: count + COUNT_FACTOR };
  },
};

export default function reducer(state = initialState, action) {
  const f = reducers[action.type];

  if (!f) {
    return state;
  }

  return f(state, action);
}
