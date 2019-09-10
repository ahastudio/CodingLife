const { Worker } = require('worker_threads');

const work = (name, workerData) => new Promise((resolve, reject) => {
  const worker = new Worker(`./workers/${name}.js`, { workerData });
  worker.on('message', resolve);
  worker.on('error', reject);
  worker.postMessage('start');
});

module.exports = {
  work,
};
