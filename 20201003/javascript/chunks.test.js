function chunk(numbers) {
  const aux = (acc, xs, max) => {
    if (!xs.length) {
      return acc;
    }
    if (max < Math.min(...xs)) {
      return acc;
    }
    const [head, ...tail] = xs;
    return aux([...acc, head], tail, Math.max(max, head));
  }

  return aux([], numbers, numbers[0]);
}

function chunks(numbers) {
  if (!numbers.length) {
    return numbers;
  }
  const c = chunk(numbers);
  return [c, ...chunks(numbers.slice(c.length))];
}

function chunks_count(numbers) {
  return chunks(numbers).length
}

test('sample', () => {
  expect(chunks_count([4, 3, 2, 1, 0])).toBe(1);
  expect(chunks_count([1, 0, 2, 3, 4])).toBe(4);
});

test('chunks', () => {
  expect(chunks([1, 0, 2, 3, 4])).toEqual([[1, 0], [2], [3], [4]]);
});

test('chunk', () => {
  expect(chunk([4, 3, 2, 1, 0])).toEqual([4, 3, 2, 1, 0]);
  expect(chunk([1, 0, 2, 3, 4])).toEqual([1, 0]);
  expect(chunk([0, 1, 2, 3, 4])).toEqual([0]);
  expect(chunk([2, 3, 4])).toEqual([2]);
});
