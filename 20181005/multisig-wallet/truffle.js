const HDWalletProvider = require('truffle-hdwallet-provider');

const mnemonic = process.env.HDWP_MNEMONIC;
const endpoint = process.env.HDWP_ENDPOINT;

module.exports = {
  networks: {
    test: {
      host: '127.0.0.1',
      port: 18545,
      network_id: '*',
      gas: 4712388,
      gasPrice: 100000000000
    },
    development: {
      host: '127.0.0.1',
      port: 8545,
      network_id: '*',
      gas: 4712388,
      gasPrice: 100000000000
    },
    ropsten: {
      provider: new HDWalletProvider(mnemonic, endpoint),
      network_id: 3,
    }
  }
};
