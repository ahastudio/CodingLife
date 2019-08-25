import { Counter } from './counter';

test('counter', () => {
  const counter = Counter();

  expect(counter()).toBe(1);
  expect(counter()).toBe(2);
  expect(counter()).toBe(3);
});
