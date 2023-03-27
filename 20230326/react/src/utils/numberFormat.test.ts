import numberFormat from './numberFormat';

test('numberFormat', () => {
  expect(numberFormat(1)).toBe('1');
  expect(numberFormat(100)).toBe('100');
  expect(numberFormat(1000)).toBe('1,000');
  expect(numberFormat(123000)).toBe('123,000');
  expect(numberFormat(1234000)).toBe('1,234,000');
});
