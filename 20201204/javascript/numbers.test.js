function sum(xs) {
  return xs.reduce((a, b) => a + b);
}

function sortedUniq(xs) {
  const values = [...xs].sort((a, b) => a - b);
  return values.filter((x, i) => x !== values[i - 1]);
}

function removeAt(xs, index) {
  return [...xs.slice(0, index), ...xs.slice(index + 1)];
}

function solve(numbers) {
  return numbers.length === 2 ? [sum(numbers)]
    : sortedUniq(numbers.flatMap((_, i) => solve(removeAt(numbers, i))));
}

// TEST -----------------------------------------------------------------------

test('sum', () => {
  expect(sum([1, 2, 3, 4])).toBe(10);
});

test('sortedUniq', () => {
  expect(sortedUniq([1, 2])).toEqual([1, 2]);
  expect(sortedUniq([1, 1, 2])).toEqual([1, 2]);
  expect(sortedUniq([1, 2, 1])).toEqual([1, 2]);
});

test('removeAt', () => {
  expect(removeAt([1, 2, 3], 0)).toEqual([2, 3]);
  expect(removeAt([1, 2, 3], 1)).toEqual([1, 3]);
  expect(removeAt([1, 2, 3], 2)).toEqual([1, 2]);
});

test('simple', () => {
  expect(solve([1, 2])).toEqual([3]);
  expect(solve([1, 2, 3])).toEqual([3, 4, 5]);
});

test('sample', () => {
  expect(solve([2, 1, 3, 4, 1])).toEqual([2, 3, 4, 5, 6, 7]);
  expect(solve([5, 0, 2, 7])).toEqual([2, 5, 7, 9, 12]);
});
