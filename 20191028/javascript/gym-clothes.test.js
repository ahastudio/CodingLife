const solution1 = (n, lost, reserve) => {
  const realLost = diff(lost, reserve).sort();
  return realLost.reduce(({ count, spares }, i) => ({
    count: count + (spares[i - 1] || spares[i + 1] ? 1 : 0),
    spares: spares[i - 1] ? spares : { ...spares, [i + 1]: false },
  }), {
    count: n - realLost.length,
    spares: lost.reduce((a, e) => ({ ...a, [e]: 0 }), counter(reserve)),
  }).count;
};

const solution2 = (n, lost, reserve) => {
  const blanks = diff(lost, reserve).sort();
  const spares = counter(diff(reserve, lost)); // <-- mutable!
  const cover = x => spares[x] && (spares[x] -= 1, true);
  return n - blanks.filter(i => ![i - 1, i + 1].some(cover)).length;
};

const counter = xs => xs.reduce((a, e) => ({ ...a, [e]: (a[e] || 0) + 1 }), {});

const diff = (a, b) => {
  const o = counter(b);
  return a.filter(i => !o[i]);
};

test('sample', () => {
  [solution1, solution2].forEach(solution => {
    expect(solution(5, [2, 4], [1, 3, 5])).toBe(5);
    expect(solution(5, [2, 4], [3])).toBe(4);
    expect(solution(5, [2, 3, 5], [4])).toBe(3);
    expect(solution(5, [5, 2, 3], [4])).toBe(3);
    expect(solution(3, [3], [1])).toBe(2);
    expect(solution(3, [1], [3])).toBe(2);
    expect(solution(3, [1], [2])).toBe(3);
    expect(solution(3, [1, 2], [2, 3])).toBe(2);
    expect(solution(3, [1, 2, 3], [2, 3])).toBe(2);
    expect(solution(3, [1, 2, 3], [1, 2, 3])).toBe(3);
  });
});

test('counter', () => {
  expect(counter([1, 2, 3])).toEqual({ [1]: 1, [2]: 1, [3]: 1 });
  expect(counter([1, 2, 2, 3])).toEqual({ [1]: 1, [2]: 2, [3]: 1 });
});

test('diff', () => {
  expect(diff([1, 2, 3], [1, 3, 5])).toEqual([2]);
});

