const DIRS = [
  { x: 1, y: 0 },
  { x: -1, y: 1 },
  { x: 0, y: 1 },
  { x: 1, y: -1 },
];

function range(n) {
  return [...Array(n)].map((_, i) => i);
}

function inRange(x, n) {
  return x >= 0 && x < n;
}

function matrix(n) {
  const m = range(n).map(() => range(n).map(() => 0));
  m[0][0] = 1;

  let posX = 0;
  let posY = 0;
  let direction = 0;

  for (let i = 1; i < n * n;) {
    const dir = DIRS[direction % DIRS.length];

    const x = posX + dir.x;
    const y = posY + dir.y;

    if (!inRange(x, n) || !inRange(y, n) || m[y][x] > 0) {
      direction += 1;
      continue;
    }

    i += 1;
    m[y][x] = i;

    posX = x;
    posY = y;
    if (dir.x === 0 || dir.y === 0) {
      direction += 1;
    }
  }

  return m;
}

// ----

function sum(n) {
  return (n + 1) * n / 2;
}

function solution(n, r, c) {
  const x = c - 1;
  const y = r - 1;
  const z = x + y;
  if (z < n) {
    return sum(z) + (z % 2 === 0 ? x : z - x) + 1;
  }
  return (n * n) - solution(n, n - y, n - x) + 1;
}

// TEST

// const test = () => {};

test('simple', () => {
  expect(solution(3, 1, 1)).toBe(1);
  expect(solution(3, 1, 2)).toBe(2);
  expect(solution(3, 2, 1)).toBe(3);
  expect(solution(3, 3, 1)).toBe(4);
  expect(solution(3, 2, 2)).toBe(5);
  expect(solution(3, 1, 3)).toBe(6);
  expect(solution(3, 2, 3)).toBe(7);
  expect(solution(3, 3, 2)).toBe(8);
  expect(solution(3, 3, 3)).toBe(9);
});

test('sample', () => {
  expect(solution(5, 3, 2)).toBe(9);
  expect(solution(5, 5, 3)).toBe(20);
  expect(solution(5, 3, 5)).toBe(22);
  expect(solution(5, 4, 5)).toBe(23);
  expect(solution(5, 5, 5)).toBe(25);

  expect(solution(6, 5, 4)).toBe(29);
  expect(solution(6, 6, 6)).toBe(36);

  expect(solution(100, 5, 4)).toBe(33);

  expect(solution(1_000, 5, 4)).toBe(33);
  expect(solution(1_000, 10, 10)).toBe(181);
  expect(solution(1_000, 999, 999)).toBe(999_996);
  expect(solution(1_000, 999, 1_000)).toBe(999_998);
  expect(solution(1_000, 1_000, 999)).toBe(999_999);
  expect(solution(1_000, 1_000, 1_000)).toBe(1_000_000);

  expect(solution(100_000, 5, 4)).toBe(33);

  expect(solution(1_000_000, 5, 4)).toBe(33);

  expect(solution(10_000_000, 5, 4)).toBe(33);
});

test('sum', () => {
  expect(sum(10)).toBe(55);
  expect(sum(100)).toBe(5050);
});

test('sum', () => {
  expect(sum(10)).toBe(55);
  expect(sum(100)).toBe(5050);
});

test('matrix', () => {
  expect(matrix(3)).toEqual([
    [1, 2, 6],
    [3, 5, 7],
    [4, 8, 9],
  ]);
});

test('range', () => {
  expect(range(3)).toEqual([0, 1, 2]);
});

test('inRange', () => {
  expect(inRange(0, 2)).toBeTruthy();
  expect(inRange(1, 2)).toBeTruthy();

  expect(inRange(-1, 2)).toBeFalsy();
  expect(inRange(2, 2)).toBeFalsy();
});
