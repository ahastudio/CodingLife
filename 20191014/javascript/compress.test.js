const solution = s => {
  const range = [...Array(s.length)].map((_, i) => i + 1);
  return Math.min(...range.map(i => compress(s, i).length));
};

const compress = (s, n) => {
  const make = ([a, l, c]) => `${a}${c > 1 ? c : ''}${l}`;
  return make(
    chunk(s, n).reduce(
      ([a, l, c], e) => e === l ? [a, l, c + 1] : [make([a, l, c]), e, 1],
      ['', '', 0]
    )
  );
};

const chunk = (s, n) =>
  s.length <= n ? [s] : [s.slice(0, n), ...chunk(s.slice(n), n)];

test('sample', () => {
  expect(solution('aabbaccc')).toBe(7);
  expect(solution('ababcdcdababcdcd')).toBe(9);
  expect(solution('abcabcdede')).toBe(8);
  expect(solution('abcabcabcabcdededededede')).toBe(14);
  expect(solution('xababcdcdababcdcd')).toBe(17);
});

test('compress', () => {
  expect(compress('aabbaccc', 1)).toBe('2a2ba3c');
  expect(compress('abcabcdede', 1)).toBe('abcabcdede');
  expect(compress('abcabcdede', 2)).toBe('abcabc2de');
  expect(compress('abcabcdede', 3)).toBe('2abcdede');
  expect(compress('ababcdcdababcdcd', 2)).toBe('2ab2cd2ab2cd');
  expect(compress('ababcdcdababcdcd', 8)).toBe('2ababcdcd');
});

test('chunk', () => {
  expect(chunk('a', 1)).toEqual(['a']);
  expect(chunk('abc', 1)).toEqual(['a', 'b', 'c']);
  expect(chunk('abc', 2)).toEqual(['ab', 'c']);
  expect(chunk('abcabcdede', 3)).toEqual(['abc', 'abc', 'ded', 'e']);
});
