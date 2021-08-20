function append(xs, x) {
  return [...xs, x];
}

function addItem(state, { payload: item }) {
  const items = append(state.items, item);
  return {
    count: items.length,
    items,
  };
}

test('append', () => {
  expect(append([], 1)).toEqual([1]);
  expect(append([1], 2)).toEqual([1, 2]);
  expect(append([1, 2], 3)).toEqual([1, 2, 3]);

  const item = {};
  expect(append([], item)[0]).toBe(item);
  expect(append(['first'], item)[1]).toBe(item);
});

test('addItem', () => {
  const initialState = {
    count: 0,
    items: [],
  };

  const item = {
    name: 'New Item',
  };

  const state = addItem(initialState, { payload: item });

  expect(state.count).toBe(1);
  expect(state.items).toHaveLength(1);
  expect(state.items[0]).toBe(item);
});
