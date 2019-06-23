const store = require('store');
const localForage = require('localforage');

test('store.js', async () => {
  store.set('hello', 'world');
  const data = store.get('hello');
  expect(data).toBe('world');
});

test('localForage', async () => {
  await localForage.setItem('hello', 'world');
  const data = await localForage.getItem('hello');
  expect(data).toBe('world');
});
