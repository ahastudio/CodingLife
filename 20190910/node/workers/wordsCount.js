const { parentPort, workerData } = require('worker_threads');

const identity = x => x;

const main = ({ text }) => {
  return {
    count: text.split(/\s+/).filter(identity).length,
    startBlank: !!text.match(/^\s/),
    endBlank: !!text.match(/\s$/),
  };
};

parentPort.postMessage(main(workerData));
