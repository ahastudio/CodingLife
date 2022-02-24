const HDWalletProvider = require('@truffle/hdwallet-provider');

const provider = new HDWalletProvider({
  mnemonic: {
    phrase: process.env.HDWP_MNEMONIC,
  },
  providerOrUrl: process.env.HDWP_ENDPOINT,
});

module.exports = {
  networks: {
    test: {
      host: '127.0.0.1',
      port: 18545,
      network_id: '*',
      gas: 4712388,
      gasPrice: 100000000000,
    },
    development: {
      host: '127.0.0.1',
      port: 8545,
      network_id: '*',
      gas: 4712388,
      gasPrice: 100000000000,
    },
    ropsten: {
      provider: provider,
      network_id: 3,
    },
  },
  compilers: {
    solc: {
      version: '0.8.0',
    },
  },
};
