const buddyStrings = (s1, s2) => {
  if (s1.length !== s2.length) {
    return false;
  }

  const diff = zip(s1, s2).reduce((acc, [x, y]) => (
    x === y ? acc : [...acc, [x, y]]
  ), []);
  return diff.length == 0 && hasDuplication(s1) ||
         diff.length == 2 && isSwap(...diff);
};

const zip = (a, b) => [...Array(a.length)].map((_, i) => [a[i], b[i]]);

const isSwap = (a, b) => a[0] === b[1] && a[1] === b[0];

const hasDuplication = s =>
  Object.values(Array.from(s).reduce((acc, x) =>
    ({ ...acc, [x]: (acc[x] || 0) + 1 }), {})
  ).some(i => i >= 2);

test('buddyStrings', () => {
  expect(buddyStrings('', 'aa')).toBeFalsy();
  expect(buddyStrings('ab', 'ba')).toBeTruthy();
  expect(buddyStrings('ab', 'ab')).toBeFalsy();
  expect(buddyStrings('ab', 'bc')).toBeFalsy();
  expect(buddyStrings('ab', 'aa')).toBeFalsy();
  expect(buddyStrings('aa', 'aa')).toBeTruthy();
  expect(buddyStrings('aaaaaaabc', 'aaaaaaacb')).toBeTruthy();
  expect(buddyStrings('aaaaaaabc', 'aaaaaabca')).toBeFalsy();
});

test('zip', () => {
  expect(zip('ab', 'cd')).toEqual([['a', 'c'], ['b', 'd']]);
});

test('hasDuplication', () => {
  expect(hasDuplication('aa')).toBeTruthy();
  expect(hasDuplication('ab')).toBeFalsy();
});

test('isSwap', () => {
  expect(isSwap(['a', 'b'], ['b', 'a'])).toBeTruthy();
  expect(isSwap(['a', 'b'], ['a', 'b'])).toBeFalsy();
  expect(isSwap(['a', 'b'], ['b', 'c'])).toBeFalsy();
});
