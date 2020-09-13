function add(x, y) {
  return x + y;
}

test('add', () => {
  expect(add(1, 2)).toBe(3);
});
