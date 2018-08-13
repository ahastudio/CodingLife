/* global artifacts */

const MyToken = artifacts.require('./MyToken.sol');

module.exports = (deployer) => {
  deployer.deploy(MyToken);
};
