import {
  spiralMatrix, spiralPoints, movePoint, checkPoint,
} from './spiral';

const context = describe;

describe('spiralMatrix', () => {
  context('when n = 1', () => {
    it('returns spiral matrix', () => {
      expect(spiralMatrix(1)).toEqual([
        [0],
      ]);
    });
  });

  context('when n = 2', () => {
    it('returns spiral matrix', () => {
      expect(spiralMatrix(2)).toEqual([
        [0, 1],
        [3, 2],
      ]);
    });
  });

  context('when n = 3', () => {
    it('returns spiral matrix', () => {
      expect(spiralMatrix(3)).toEqual([
        [0, 1, 2],
        [7, 8, 3],
        [6, 5, 4],
      ]);
    });
  });

  context('when n = 5', () => {
    it('returns spiral matrix', () => {
      expect(spiralMatrix(5)).toEqual([
        [0, 1, 2, 3, 4],
        [15, 16, 17, 18, 5],
        [14, 23, 24, 19, 6],
        [13, 22, 21, 20, 7],
        [12, 11, 10, 9, 8],
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

describe('movePoint', () => {
  it('returns new move state', () => {
    expect(movePoint(2, {
      x: 1, y: 0, dx: 1, dy: 0, points: [],
    }))
      .toEqual({
        x: 1, y: 1, dx: -0, dy: 1, points: [[1, 0]],
      });
  });
});

describe('checkPoint', () => {
  it('returns possibility to move', () => {
    expect(checkPoint(2, { x: 0, y: 0, points: [] })).toBeTruthy();
    expect(checkPoint(2, { x: 1, y: 1, points: [] })).toBeTruthy();
    expect(checkPoint(2, { x: 2, y: 0, points: [] })).toBeFalsy();
    expect(checkPoint(2, { x: -1, y: 0, points: [] })).toBeFalsy();
    expect(checkPoint(2, { x: 0, y: 0, points: [[0, 0]] })).toBeFalsy();
  });
});
