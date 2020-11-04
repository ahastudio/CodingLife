const { add, isPalindrome, range, at } = require('./demo');

test('test name', () => {
  expect(add(1, 2)).toBe(3);
  expect(add(3, 4)).toBe(7);
  expect(add(8, 4)).toBe(12);
  expect(add(12, 34)).toBe(46);
});

test('palindrome', () => {
  expect(isPalindrome('ab')).toBeFalsy();
  expect(isPalindrome('abc')).toBeFalsy();
  expect(isPalindrome('abab')).toBeFalsy();
  expect(isPalindrome('abca')).toBeFalsy();
  expect(isPalindrome('abcab')).toBeFalsy();
  expect(isPalindrome('abccab')).toBeFalsy();
  expect(isPalindrome('abcdabc')).toBeFalsy();
  expect(isPalindrome('abcdefghijkabcdefghij')).toBeFalsy();

  expect(isPalindrome('')).toBeTruthy();
  expect(isPalindrome('a')).toBeTruthy();
  expect(isPalindrome('aa')).toBeTruthy();
  expect(isPalindrome('aba')).toBeTruthy();
  expect(isPalindrome('abba')).toBeTruthy();
  expect(isPalindrome('abcba')).toBeTruthy();
  expect(isPalindrome('abccba')).toBeTruthy();
  expect(isPalindrome('abcdcba')).toBeTruthy();
  expect(isPalindrome('abcdefghijkjihgfedcba')).toBeTruthy();
});

test('range', () => {
  expect(range(5)).toEqual([0, 1, 2, 3, 4]);
});

describe('at', () => {
  describe('with positive index number', () => {
    it('returns element at index', () => {
      expect(at([1, 2, 3], 0)).toBe(1);
      expect(at([1, 2, 3], 2)).toBe(3);
    });
  });

  describe('with negative index number', () => {
    it('returns element at index from the last', () => {
      expect(at([1, 2, 3], -1)).toBe(3);
      expect(at([1, 2, 3], -3)).toBe(1);
    });
  });

  describe('with out of bound', () => {
    it('returns undefined', () => {
      expect(at([1, 2, 3], 10)).toBeUndefined();
      expect(at([1, 2, 3], -10)).toBeUndefined();
    });
  });
});
