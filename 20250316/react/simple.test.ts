import { expect, test } from 'vitest';

function add(x: number, y: number): number {
  return x + y;
}

test('simple test', () => {
  expect(add(1, 2)).toBe(3);
});
