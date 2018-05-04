const Bank = artifacts.require("./Bank.sol");

module.exports = function(deployer) {
  deployer.deploy(Bank);
};
