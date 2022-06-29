import { fetchExampleText, upper } from './http';

test('fetchExampleText', async () => {
  const text = await fetchExampleText();
  expect(text).toMatch('Example Domain');
});

test('upper', () => {
  expect(upper('abdc')).toBe('ABDC');
});
