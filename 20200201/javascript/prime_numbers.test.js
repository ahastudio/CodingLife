function solution(n) {
  return [...eratos(rangeTo(2, n))].length;
}

function* eratos(it) {
  const x = it.next().value;
  if (!x) {
    return;
  }
  yield x;
  for (const i of eratos(minus(it, step(x)))) {
    yield i;
  }
}

function* rangeTo(start, end) {
  for (let i = start; i <= end; i++) {
    yield i;
  }
}

function* step(x) {
  for (let i = x; ; i += x) {
    yield i;
  }
}

function* minus(x, y) {
  let a = x.next().value;
  let b = y.next().value;
  while (a) {
    if (a > b) {
      b = y.next().value;
      continue;
    }
    if (a !== b) {
      yield a;
    }
    a = x.next().value;
  }
}

function* take(it, n) {
  for (let i = 0; i < n; i++) {
    yield it.next().value;
  }
}

test('solution', () => {
  expect(solution(2)).toBe(1);
  expect(solution(3)).toBe(2);
  expect(solution(4)).toBe(2);
  expect(solution(5)).toBe(3);
  expect(solution(10)).toBe(4);
  expect(solution(100)).toBe(25);
  expect(solution(1000)).toBe(168);
  expect(solution(10000)).toBe(1229);
  // expect(solution(100000)).toBe(9592);
  // expect(solution(1000000)).toBe(78498);
});

test('rangeTo', () => {
  expect([...rangeTo(2, 10)]).toEqual([2, 3, 4, 5, 6, 7, 8, 9, 10]);
});

test('step', () => {
  expect([...take(step(2), 3)]).toEqual([2, 4, 6]);
  expect([...take(step(5), 3)]).toEqual([5, 10, 15]);
});

test('minus', () => {
  expect(
    [...take(minus(step(1), step(2)), 5)]
  ).toEqual([1, 3, 5, 7, 9]);
  expect(
    [...take(minus(minus(step(1), step(2)), step(3)), 5)]
  ).toEqual([1, 5, 7, 11, 13]);
  expect(
    [...minus(rangeTo(2, 10), step(3))]
  ).toEqual([2, 4, 5, 7, 8, 10]);
});
