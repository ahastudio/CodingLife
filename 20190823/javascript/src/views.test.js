import { render } from './views';

test('simple render', () => {
  const html = render({
    tasks: [],
  });
  expect(html).not.toContain('<ul>');
  expect(html).toContain('<button');
});

test('render tasks', () => {
  const html = render({
    tasks: [
      { id: 1, title: 'Hello', completed: false },
    ],
  });
  expect(html).toContain('<ul>');
  expect(html).toContain('Hello');
});
