/* global describe it expect */

const range = (n) => [...Array(n)].map((_, i) => i);

const spiralMatrix = (n) => {
  const m = [...Array(n)].map(() => [...Array(n)]);
  spiralPoints(n).forEach(([x, y], i) => m[y][x] = i);
  return m;
};

const directions = [
  [1, 0],
  [0, 1],
  [-1, 0],
  [0, -1],
];

const initialState = {
  x: 0,
  y: 0,
  direction: 0,
  points: [],
};

const spiralPoints = (n) =>
  range(n * n).reduce(({ x, y, direction, points }) => Object.assign({},
    movePoint(n, { x, y, direction, points }),
    { points: [...points, [x, y]] }
  ), initialState).points;

const movePoint = (n, { x, y, direction, points }) => {
  let [dx, dy] = directions[direction];
  if (!checkPoint(n, points, x + dx, y + dy)) {
    direction = (direction + 1) % directions.length;
    [dx, dy] = directions[direction];
  }
  x += dx;
  y += dy;
  return { x, y, direction };
};

const checkPoint = (n, points, x, y) =>
  x >= 0 && x < n && y < n && !points.find(([px, py]) => x == px && y == py);

describe('spiralMatrix', () => {
  describe('when n = 1', () => {
    it('returns spiral matrix', () => {
      expect(spiralMatrix(1)).toEqual([
        [0],
      ]);
    });
  });

  describe('when n = 2', () => {
    it('returns spiral matrix', () => {
      expect(spiralMatrix(2)).toEqual([
        [0, 1],
        [3, 2],
      ]);
    });
  });

  describe('when n = 3', () => {
    it('returns spiral matrix', () => {
      expect(spiralMatrix(3)).toEqual([
        [0, 1, 2],
        [7, 8, 3],
        [6, 5, 4],
      ]);
    });
  });

  describe('when n = 5', () => {
    it('returns spiral matrix', () => {
      expect(spiralMatrix(5)).toEqual([
        [0,  1,  2,  3,  4],
        [15, 16, 17, 18, 5],
        [14, 23, 24, 19, 6],
        [13, 22, 21, 20, 7],
        [12, 11, 10, 9,  8],
      ]);
    });
  });
});

describe('spiralPoints', () => {
  it('returns points', () => {
    expect(spiralPoints(1)).toEqual([[0, 0]]);
    expect(spiralPoints(2)).toEqual([[0, 0], [1, 0], [1, 1], [0, 1]]);
    expect(spiralPoints(3)).toEqual([
      [0, 0], [1, 0], [2, 0], [2, 1], [2, 2], [1, 2], [0, 2], [0, 1], [1, 1],
    ]);
  });
});

describe('checkPoint', () => {
  it('returns posibble to move', () => {
    expect(checkPoint(2, [], 1, 1)).toBeTruthy();
    expect(checkPoint(2, [], 2, 0)).toBeFalsy();
    expect(checkPoint(2, [], -1, 0)).toBeFalsy();
    expect(checkPoint(2, [[0, 0]], 0, 0)).toBeFalsy();
  });
});

describe('range', () => {
  it('returns array from 0 to n - 1', () => {
    expect(range(1)).toEqual([0]);
    expect(range(3)).toEqual([0, 1, 2]);
  });
});
