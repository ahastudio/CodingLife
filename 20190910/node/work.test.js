const fs = require('fs');
const { work } = require('./work');

test('add', async () => {
  const result = await work('add', { x: 3, y: 5 });

  expect(result).toBe(3 + 5);
});

test('wordsCount', async () => {
  const result = await work('wordsCount', { text: 'This is ' });

  expect(result).toEqual({ count: 2, startBlank: false, endBlank: true });
});

const mapped = chunks => Promise.all(
  chunks.map(text => work('wordsCount', { text }))
);

const reduceFunc = (acc, { count, startBlank, endBlank }) => ({
  count: acc.count + count - (!acc.endBlank && !startBlank ? 1 : 0),
  endBlank,
});

test('count all words #0', async () => {
  const chunks = ['This'];

  const { count } = (await mapped(chunks)).reduce(reduceFunc);

  expect(count).toBe(1);
});

test('count all words #1', async () => {
  const chunks = ['This ', 'is a ', 'pen'];

  const { count } = (await mapped(chunks)).reduce(reduceFunc);

  expect(count).toBe(4);
});

test('count all words #2', async () => {
  const chunks = ['This i', 's a pe', 'n'];

  const { count } = (await mapped(chunks)).reduce(reduceFunc);

  expect(count).toBe(4);
});

test('count all words #3', async () => {
  const chunks = ['This', 'isa', 'pen'];

  const { count } = (await mapped(chunks)).reduce(reduceFunc);

  expect(count).toBe(1);
});

test('count all words for large case', async () => {
  const body = await fs.promises.readFile('./data/text.txt');
  const text = body.toString();

  const chunks = text.match(/(.|[\r\n]){1,100}/g);

  const { count } = (await mapped(chunks)).reduce(reduceFunc);

  expect(count).toBe(598);
});
