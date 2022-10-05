function range(n) {
  return [...Array(n)].map((_, i) => i);
}

function equals(expected) {
  return (actual) => actual.join() === expected.join();
}

const initialState = {
  x: 0,
  y: 0,
  dx: 1,
  dy: 0,
  points: [],
};

export function checkPoint(n, { x, y, points }) {
  return range(n).includes(x) && range(n).includes(y)
    && !points.find(equals([x, y]));
}

function delta(n, state) {
  const { dx, dy } = state;
  return checkPoint(n, {
    x: state.x + dx,
    y: state.y + dy,
    points: state.points,
  }) ? { dx, dy } : { dx: -dy, dy: dx };
}

export function movePoint(n, state) {
  const { dx, dy } = delta(n, state);
  const { x, y, points } = state;
  return {
    x: x + dx,
    y: y + dy,
    dx,
    dy,
    points: [...points, [x, y]],
  };
}

export function spiralPoints(n) {
  return range(n * n)
    .reduce((state) => movePoint(n, state), initialState)
    .points;
}

export function spiralMatrix(n) {
  const m = [...Array(n)].map(() => [...Array(n)]);
  // eslint-disable-next-line no-return-assign
  spiralPoints(n).forEach(([x, y], i) => m[y][x] = i);
  return m;

  // OR

  // const points = spiralPoints(n);
  // return range(n).map((y) => (
  //   range(n).map((x) => (
  //     points.findIndex(equals([x, y]))
  //   ))
  // ));
}
