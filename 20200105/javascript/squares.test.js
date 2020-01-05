const squares = (w, h) => w * h - w - h + gcd(w, h);
const gcd = (a, b) => b === 0 ? a : gcd(b, a % b);

// test ------------------------------------------------

test('sample', () => {
  expect(squares(8, 12)).toBe(80);
});

test('gcd', () => {
  expect(gcd(1, 1)).toBe(1);
  expect(gcd(2, 3)).toBe(1);
  expect(gcd(2, 4)).toBe(2);
  expect(gcd(8, 12)).toBe(4);
});
