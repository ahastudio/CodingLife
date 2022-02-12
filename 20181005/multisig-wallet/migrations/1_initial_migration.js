/* global artifacts */

const Migrations = artifacts.require('./Migrations.sol');

module.exports = async (deployer) => {
  try {
    await deployer.deploy(Migrations);
  } catch (e) {
    console.error(e);
  }
};
