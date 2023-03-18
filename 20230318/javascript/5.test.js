function gcd(x, y) {
  // Hmm...
  if (y === 0) {
    return x;
  }

  if (x % y === 0) {
    return y;
  }

  return gcd(y, x % y);
}

function makeTargets(positions) {
  const targets = {};

  positions.forEach(([x, y]) => {
    const z = gcd(Math.abs(x), Math.abs(y));
    const pos = `${x / z},${y / z}`;
    const distance = x ** 2 + y ** 2;
    if (!targets[pos]) {
      targets[pos] = [];
    }
    targets[pos].push(distance);
  });

  Object.keys(targets).forEach(pos => {
    targets[pos].sort((a, b) => a - b);
  });

  return targets;
}

function solution(monsters, bullets) {
  let answer = 0;

  const targets = makeTargets(monsters);

  for (let [x, y] of bullets) {
    const z = gcd(Math.abs(x), Math.abs(y));
    const pos = `${x / z},${y / z}`;
    const xs = targets[pos];
    if (!xs || !xs.length) {
      continue;
    }
    xs.shift();
    answer += 1;
  }

  if (answer === 0) {
    return -1;
  }

  return answer;
}

// TEST

// const test = () => {};

test('sample', () => {
  expect(solution([
    [2, 3], [4, 5], [-3, 3], [2, -4], [3, -6], [-3, -3], [-5, 0], [-4, 4]
  ], [
    [4, 1], [4, 6], [1, -2], [-4, -4], [-3, 0], [-4, 4]
  ])).toBe(5);
});

test('makeTargets', () => {
  expect(makeTargets([[1, 1], [3, 3], [2, 1]])).toEqual({
    '1,1': [1 ** 2 + 1 ** 2, 3 ** 2 + 3 ** 2],
    '2,1': [2 ** 2 + 1 ** 2],
  });
});

test('gcd', () => {
  expect(gcd(1, 3)).toBe(1);
  expect(gcd(2, 3)).toBe(1);
  expect(gcd(2, 4)).toBe(2);
  expect(gcd(60, 48)).toBe(12);

  // What?
  expect(gcd(10, 0)).toBe(10);
});
