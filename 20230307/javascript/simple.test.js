function primeNumbers(max) {
  const numbers = new Set([...Array(max - 1)].map((_, i) => i + 2));

  for (let i = 2; i < max; i += 1) {
    if (!numbers.has(i)) {
      continue;
    }
    for (let j = i * 2; j <= max; j += i) {
      numbers.delete(j);
    }
  }

  return [...numbers.values()];
}

function countPrimeNumbers1(max) {
  return primeNumbers(max).length;
}

function countPrimeNumbers2(max) {
  const numbers = Array(max + 1).fill(1);
  numbers[0] = 0;
  numbers[1] = 0;

  for (let i = 2; i < max; i += 1) {
    if (!numbers[i]) {
      continue;
    }
    for (let j = i * 2; j <= max; j += i) {
      numbers[j] = 0;
    }
  }

  return numbers.reduce((a, b) => a + b);
}

test('countPrimeNumbers', () => {
  [countPrimeNumbers1, countPrimeNumbers2].forEach(countPrimeNumbers => {
    expect(countPrimeNumbers(3)).toBe(2);
    expect(countPrimeNumbers(10)).toBe(4);
    expect(countPrimeNumbers(20)).toBe(8);
    expect(countPrimeNumbers(25)).toBe(9);
    expect(countPrimeNumbers(100)).toBe(25);
    expect(countPrimeNumbers(1_000)).toBe(168);
    expect(countPrimeNumbers(100_000)).toBe(9_592);
    expect(countPrimeNumbers(1_000_000)).toBe(78_498);
  });
});

test('countPrimeNumbers1', () => {
  expect(countPrimeNumbers1(1_000_000)).toBe(78_498);
});

test('countPrimeNumbers2', () => {
  expect(countPrimeNumbers2(1_000_000)).toBe(78_498);
});

test('primeNumbers', () => {
  expect(primeNumbers(3)).toEqual([2, 3]);
  expect(primeNumbers(10)).toEqual([2, 3, 5, 7]);
});
