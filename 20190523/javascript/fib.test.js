// Rest element must be last element -> use reverse. (BAD PARTS)
const fib = n => (n <= 2)
  ? Array(Math.max(n, 0)).fill(1)
  : Array(n - 2).fill(0)
    .reduce(([a, b, ...t]) => [a + b, a, b, ...t], [1, 1])
    .reverse();

test('fib', () => {
  expect(fib(1)).toEqual([1]);
  expect(fib(2)).toEqual([1, 1]);
  expect(fib(3)).toEqual([1, 1, 2]);
  expect(fib(4)).toEqual([1, 1, 2, 3]);
  expect(fib(5)).toEqual([1, 1, 2, 3, 5]);
  expect(fib(6)).toEqual([1, 1, 2, 3, 5, 8]);
  expect(fib(7)).toEqual([1, 1, 2, 3, 5, 8, 13]);
  expect(fib(0)).toEqual([]);
  expect(fib(-1)).toEqual([]);
});
