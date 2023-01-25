function add(...numbers: number[]): number {
  if (numbers.length === 0) {
    return 0;
  }

  return numbers.reduce((acc, number) => acc + number);

  // return add(...numbers.slice(0, numbers.length - 1))
  //   + numbers[numbers.length - 1];
}

const context = describe;

test('add 테스트', () => {
  // 인자가 없으면 0
  expect(add()).toBe(0);

  // 두 숫자의 합
  expect(add(1, 2)).toBe(3);

  // 세 숫자의 합도 잘 된다.
  expect(add(1, 2, 3)).toBe(6);
});

describe('add', () => {
  context('with no argument', () => {
    it('returns zero', () => {
      expect(add()).toBe(0);
    });
  });

  context('with only one argument', () => {
    it('returns the same number', () => {
      // when
      const result = add(2);

      // then
      expect(result).toBe(2);
    });
  });

  context('with two arguments', () => {
    it('returns sum of two numbers', () => {
      expect(add(1, 2)).toBe(3);
    });
  });

  context('with three arguments', () => {
    it('returns sum of three numbers', () => {
      expect(add(1, 2, 3)).toBe(6);
    });
  });

  context('with ten arguments', () => {
    it('returns sum of ten numbers', () => {
      expect(add(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).toBe(55);
    });
  });
});
