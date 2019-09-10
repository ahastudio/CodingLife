const { parentPort, workerData } = require('worker_threads');

const main = ({ x, y }) => {
  return x + y;
};

parentPort.postMessage(main(workerData));
