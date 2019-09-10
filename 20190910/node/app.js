const fs = require('fs');
const { work } = require('./work');

const mapped = chunks => Promise.all(
  chunks.map(text => work('wordsCount', { text }))
);

const reduceFunc = (acc, { count, startBlank, endBlank }) => ({
  count: acc.count + count - (!acc.endBlank && !startBlank ? 1 : 0),
  endBlank,
});

(async () => {
  const filename = process.argv[2];

  const body = await fs.promises.readFile(filename);
  const text = body.toString();
  const size = Math.floor(text.length / 10);
  const chunks = text.match(new RegExp(`(.|[\\r\\n]){1,${size}}`, 'g'));
  const { count } = (await mapped(chunks)).reduce(reduceFunc);

  console.log(`Words count: ${count}`);
})();
