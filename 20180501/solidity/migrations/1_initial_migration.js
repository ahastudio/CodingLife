const Web3 = require('web3');

const TruffleConfig = require('../truffle');

const Migrations = artifacts.require("./Migrations.sol");

module.exports = function(deployer, network, addresses) {
  const config = TruffleConfig.networks[network];
  const password = process.env.ACCOUNT_PASSWORD;

  if (password) {
    console.log(`Account: ${config.from.toUpperCase()}`);

    const url = `http://${config.host}:${config.port}`;
    const provider = new Web3.providers.HttpProvider(url);

    const web3 = new Web3(provider);

    web3.eth.personal.unlockAccount(config.from, password, 36000);
  }

  deployer.deploy(Migrations);
};
