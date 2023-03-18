function list(xs) {
  const head = {
    value: 0,
    prev: null,
    next: null,
  };

  xs.reduce((prev, x) => {
    const node = {
      value: x,
      prev,
      next: null,
    };
    prev.next = node;
    return node;
  }, head);

  return head;
}

function remove(node) {
  node.prev.next = node.next;

  if (node.next) {
    node.next.prev = node.prev;
  }
}

function slice(n, times) {
  let step = 1_000_000_000_000;
  const indices = [];

  for (let i = 0; i < times.length; i += 1) {
    const time = times[i];
    if (time <= 0) {
      continue;
    }

    if (time < step) {
      step = time;
    }

    indices.push(i);

    if (indices.length === n) {
      break;
    }
  }

  return [step, indices];
}

function solution(N, coffee_times) {
  const answer = [];
  let orders = [...coffee_times];

  while (answer.length < coffee_times.length) {
    const [step, indices] = slice(N, orders);

    for (let i of indices) {
      orders[i] -= step;
      if (orders[i] > 0) {
        continue;
      }
      answer.push(i + 1);
    }
  }

  return answer;
}

// TEST

// const test = () => {};

test('sample', () => {
  expect(solution(3, [4, 2, 2, 5, 3])).toEqual([2, 3, 1, 5, 4]);
  expect(solution(1, [100, 1, 50, 1, 1])).toEqual([1, 2, 3, 4, 5]);

  expect(solution(1_000, Array(100_000).fill(1_000_000)))
    .toEqual([...Array(100_000)].map((_, i) => i + 1));
});

test('slice', () => {
  expect(slice(3, [4, 2, 2, 5, 3])).toEqual([2, [0, 1, 2]]);
  expect(slice(3, [10, 0, 5, 9, 3])).toEqual([5, [0, 2, 3]]);
});

test('list', () => {
  const node0 = {
    value: 0,
    prev: null,
    next: null,
  };

  const node1 = {
    value: 1,
    prev: node0,
    next: null,
  };

  const node2 = {
    value: 2,
    prev: node1,
    next: null,
  };

  const node3 = {
    value: 3,
    prev: node2,
    next: null,
  };

  node0.next = node1;
  node1.next = node2;
  node2.next = node3;

  const head = list([1, 2, 3]);

  expect(head.next).toEqual(node1);
  expect(head.next.prev).toEqual(head);
  expect(head.next.next).toEqual(node2);
});
