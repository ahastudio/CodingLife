// function sum(numbers, start, end) {
//   return numbers.slice(start, end + 1)
//     .reduce((acc, number) => acc + number, 0);
// }

function solution(sequence, k) {
  for (let i = sequence.length - 1; i >= 0; i -= 1) {
    if (sequence[i] > k) {
      continue;
    }
    let n = 0;
    for (let j = i; j < sequence.length; j += 1) {
      // const n = sum(sequence, i, j);
      n += sequence[j];
      if (n > k) {
        break;
      }
      if (n !== k) {
        continue;
      }
      if (sequence[i] === sequence[j]) {
        const index = sequence.indexOf(sequence[i]);
        return [index, j - (i - index)];
      }
      return [i, j];
    }
  }
  return [-1, -1];
}

// TEST ----

// const test = () => {};

test('simple', () => {
  expect(solution([0], 1)).toEqual([-1, -1]);

  expect(solution([1], 1)).toEqual([0, 0]);
  expect(solution([1, 2], 3)).toEqual([0, 1]);
  expect(solution([1, 2], 1)).toEqual([0, 0]);
  expect(solution([1, 2], 2)).toEqual([1, 1]);
  expect(solution([1, 2, 3], 5)).toEqual([1, 2]);
  expect(solution([1, 2, 2], 3)).toEqual([0, 1]);
  expect(solution([1, 2, 3], 6)).toEqual([0, 2]);
  expect(solution([1, 2, 3, 4], 6)).toEqual([0, 2]);

  expect(solution([1, 1, 1], 1)).toEqual([0, 0]);
  expect(solution([1, 1, 1], 2)).toEqual([0, 1]);
  expect(solution([1, 1, 1, 2], 3)).toEqual([2, 3]);

  expect(solution([1, 2, 3, 4, 5], 7)).toEqual([2, 3]);
  expect(solution([1, 1, 1, 2, 3, 4, 5], 5)).toEqual([6, 6]);
  expect(solution([2, 2, 2, 2, 2], 6)).toEqual([0, 2]);

  expect(solution([1, 2, 3, 4, 5, 6, 7, 8, 9, 10], 9)).toEqual([8, 8]);
  expect(solution([1, 2, 3, 4, 5, 6, 7, 8, 9, 10], 15)).toEqual([6, 7]);

  expect(solution(Array(100_000).fill(1), 10_000)).toEqual([0, 10_000 - 1]);
});

// test('sum', () => {
//   expect(sum([1, 2, 3], 0, 0)).toBe(1);
//   expect(sum([1, 2, 3], 0, 1)).toBe(3);
//   expect(sum([1, 2, 3], 0, 2)).toBe(6);
//   expect(sum([1, 2, 3], 1, 2)).toBe(5);
// });
