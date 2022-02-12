/* global artifacts */

const MultiSigWalletFactory = artifacts.require('./MultiSigWalletFactory.sol');

const owners = [
  '0x6D02F341188Fb1ffBAeC44EfEFB40Afb1d43C849',
  '0xF5ED020a3E6e2C34Cec0028E0486B063A790A1a1',
  '0x45efa15bce9fae5bf92ddbb2b6a58d9fa1f7020f',
];

module.exports = async (deployer) => {
  try {
    await deployer.deploy(MultiSigWalletFactory);
  } catch (e) {
    console.error(e);
  }
};
