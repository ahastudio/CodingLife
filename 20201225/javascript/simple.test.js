const { add } = require('./simple');

test('add', () => {
  expect(add(3, 4)).toBe(7);
  expect(add(3, -4)).toBe(-1);
  expect(add(-3, -4)).toBe(-7);
  expect(add(-3, 4)).toBe(1);
});
