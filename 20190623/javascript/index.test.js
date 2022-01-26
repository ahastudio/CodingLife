const store = require('store');
const localForage = require('localforage');

test('Store.js', async () => {
  store.set('hello', 'world');
  const data = store.get('hello');
  expect(data).toBe('world');
});

test('localForage', async () => {
  // localForage는 Web Browser만 지원
  // → [ 'asyncStorage', 'webSQLStorage', 'localStorageWrapper' ]
  // 따라서 이 테스트는 실패하는 게 정상입니다.
  await localForage.setItem('hello', 'world');
  const data = await localForage.getItem('hello');
  expect(data).toBe('world');
});
