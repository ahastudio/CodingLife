function f(cache, xs, index) {
  if (index >= xs.length) {
    return 0;
  }

  if (index === xs.length - 1) {
    return xs[index];
  }

  if (!cache[index]) {
    cache[index] = Math.max(
      xs[index] + f(cache, xs, index + 2),
      f(cache, xs, index + 1),
    );
  }

  return cache[index];
}

function solution(sticker) {
  const cache = {};
  for (let i = 1; i < sticker.length; i += 1) {
    f(cache, sticker, sticker.length - i);
  }
  return f(cache, sticker, 0);
}

// TEST

// const test = () => {};

test('simple', () => {
  expect(solution([12])).toBe(12);
  expect(solution([12, 12])).toBe(12);
  expect(solution([12, 12, 12])).toBe(12 + 12);
});

test('large', () => {
  expect(solution(Array(1_000 * 2 - 1).fill(12))).toBe(12 * 1_000);
  expect(solution(Array(100_000 * 2 - 1).fill(12))).toBe(12 * 100_000);
});

test('sample', () => {
  expect(solution([12, 12, 12, 12, 12])).toBe(12 + 12 + 12);
  expect(solution([12, 80, 14, 22, 100])).toBe(80 + 100);

  expect(solution([12, 80, 14, 22, 100, 200, 70, 30, 100]))
    .toBe(80 + 22 + 200 + 100);
});
