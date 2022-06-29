import calculate, { plus, minus } from './calculate';

test('calcaulte', () => {
  expect(calculate('+', 1, 2)).toBe(3);
  expect(calculate('-', 10, 2)).toBe(8);
});

test('plus', () => {
  expect(plus(1, 2)).toBe(3);
});

test('minus', () => {
  expect(minus(10, 2)).toBe(8);
});
