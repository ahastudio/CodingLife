// [ Merge Sort ]
// 1. Sample
// 2. 반으로 계속 나누기
//    - N=짝수, [1, 2, 3, 4] -> [1, 2], [3, 4]
//    - N=홀수, [1, 2, 3, 4, 5] -> [1, 2, 3], [4, 5] or [1, 2], [3, 4, 5]
//      --> 소수점 버림? 5 / 2 = 2 --> 개수로 할까?
// 3. 합치면서 정렬 유지하기
//    - [1], [2] => [1, 2]
//    - [1, 3], [2] => [1, 2, 3]
//    - [1, 3], [2, 4] => [1, 2, 3, 4]
//    - [1, 2, 3, 4], [5] => [1, 2, 3, 4, 5] --> 작동은 하지만, 이런 일은 없음.

function range(n) {
  return [...Array(n).keys()];
}

function divide(xs) {
  const middle = Math.floor(xs.length / 2);
  return [xs.slice(0, middle), xs.slice(middle)];
}

function merge(xs, ys) {
  if (xs.length === 0) {
    return ys;
  }
  if (ys.length === 0) {
    return xs;
  }

  const size = xs.length + ys.length;

  const { sorted } = range(size).reduce(({ sorted, i, j }) => {
    const x = xs[i];
    const y = ys[j];
    return y === undefined || x !== undefined && x < y
      ? { sorted: [...sorted, x], i: i + 1, j }
      : { sorted: [...sorted, y], i: i, j: j + 1 };
  }, { sorted: [], i: 0, j: 0 });

  return sorted;
}

function sort(xs) {
  const aux = (values) => {
    const [lhs, rhs] = divide(values);
    if (lhs.length === 0) {
      return rhs;
    }
    if (rhs.length === 0) {
      return lhs;
    }
    return merge(aux(lhs), aux(rhs));
  };

  return aux(xs);
}

test('sample', () => {
  expect(sort([5, 4, 3, 2, 1])).toEqual([1, 2, 3, 4, 5]);

  expect(sort([0, 1, 9, 7, 5, 6, 8, 2, 4, 3])).toEqual(range(10));

  expect(sort(range(100).reverse())).toEqual(range(100));
  expect(sort(range(101).reverse())).toEqual(range(101));
});

describe('merge', () => {
  test('one + empty', () => {
    expect(merge([1], [])).toEqual([1]);
    expect(merge([], [1])).toEqual([1]);
  });

  test('one + one', () => {
    expect(merge([1], [2])).toEqual([1, 2]);
    expect(merge([2], [1])).toEqual([1, 2]);
  });

  test('many + many', () => {
    expect(merge([1, 2], [3])).toEqual([1, 2, 3]);
    expect(merge([1, 3], [2, 4])).toEqual([1, 2, 3, 4]);
    expect(merge([1, 2, 3, 4], [5])).toEqual([1, 2, 3, 4, 5]);
  });
});

// 배열 조작 풀이를 할 때...
// test('mergeInArray', () => {
//   const xs = [4, 3, 2, 1];
//   mergeInArray(xs, 0, 2, 1);
//   expect(xs).toEqual([3, 4, 2, 1]);
//   mergeInArray(xs, 2, 2, 3);
//   expect(xs).toEqual([3, 4, 1, 2]);
// });

test('divide', () => {
  expect(divide([1, 2])).toEqual([[1], [2]]);
  expect(divide([1, 2, 3])).toEqual([[1], [2, 3]]);
  expect(divide([1, 2, 3, 4])).toEqual([[1, 2], [3, 4]]);
  expect(divide([1, 2, 3, 4, 5])).toEqual([[1, 2], [3, 4, 5]]);
});
