import { plus, minus } from './calculate';

test('plus', () => {
  expect(plus(1, 2)).toBe(3);
});

test('minus', () => {
  expect(minus(5, 2)).toBe(3);
});
