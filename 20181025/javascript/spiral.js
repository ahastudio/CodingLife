const _ = require('lodash');

const spiralMatrix = (n) => {
  const m = [...Array(n)].map(() => [...Array(n)]);
  spiralPoints(n).forEach(([x, y], i) => m[y][x] = i);
  return m;

  // OR

  // const points = spiralPoints(n);
  // return _.range(n).map(y => _.range(n).map(x =>
  //   points.findIndex(_.matches([x, y]))
  // ));
};

const initialState = {
  x: 0,
  y: 0,
  dx: 1,
  dy: 0,
  points: [],
};

const spiralPoints = (n) =>
  _.range(n * n).reduce(state => movePoint(n, state), initialState).points;

const movePoint = (n, state) => {
  const { dx, dy } = delta(n, state);
  const { x, y, points } = state;
  return { x: x + dx, y: y + dy, dx, dy, points: [...points, [x, y]] };
};

const delta = (n, { x, y, dx, dy, points }) =>
  checkPoint(n, x + dx, y + dy, points) ? { dx, dy } : { dx: -dy, dy: dx };

const checkPoint = (n, x, y, points) =>
  _.inRange(x, n) && _.inRange(y, n) && !points.find(_.matches([x, y]));

module.exports = {
  spiralMatrix,
  spiralPoints,
  movePoint,
  checkPoint,
};
