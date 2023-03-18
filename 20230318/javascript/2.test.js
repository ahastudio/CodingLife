function solution(play_list, listen_time) {
  let answer = 0;

  for (let i = 0; i < play_list.length; i += 1) {
    let n = 1;
    let minutes = 1;

    if (listen_time <= minutes) {
      if (n > answer) {
        answer = n;
      }
      continue;
    }

    for (let j = 1; j < play_list.length; j += 1) {
      n += 1;
      minutes += play_list[(i + j) % play_list.length];
      if (minutes >= listen_time) {
        break;
      }
    }

    if (n > answer) {
      answer = n;
    }

    if (answer === play_list.length) {
      break;
    }
  }

  return answer;
}

// TEST

// const test = () => {};

test('simple', () => {
  expect(solution([], 4)).toBe(0);
  expect(solution([1], 4)).toBe(1);
  expect(solution([10], 4)).toBe(1);
  expect(solution([10, 5], 4)).toBe(2);
  expect(solution([10, 5], 1)).toBe(1);
  expect(solution([1, 1, 1, 1], 4)).toBe(4);
  expect(solution([1, 1, 1, 1], 3)).toBe(3);
  expect(solution([1, 1, 100, 1], 50)).toBe(4);
  expect(solution([1, 1, 50, 1], 100)).toBe(4);
  expect(solution([50, 1, 50, 1], 52)).toBe(3);
});

test('large', () => {
  expect(solution(Array(10_000).fill(1), 1_000)).toBe(1_000);
  expect(solution(Array(100_000).fill(1), 10_000)).toBe(10_000);
});

test('sample', () => {
  expect(solution([2, 3, 1, 4], 3)).toBe(3);
  expect(solution([1, 2, 3, 4], 5)).toBe(4);
  expect(solution([1, 2, 3, 4], 20)).toBe(4);
  expect(solution([1, 2, 3, 4], 20)).toBe(4);
});
