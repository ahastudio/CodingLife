/* global artifacts, contract, describe, beforeEach, it, assert, web3 */

const { eth } = web3;

const MultiSigWalletFactory = artifacts.require('./MultiSigWalletFactory.sol');
const MultiSigWallet = artifacts.require('./MultiSigWallet.sol');

contract('MultiSigWalletFactory', ([admin, ...accounts]) => {
  let factory;

  const name = 'test wallet';
  const owners = [1, 2, 3].map(i => accounts[i]);
  const required = 2;

  beforeEach(async () => {
    factory = await MultiSigWalletFactory.new({ from: admin });
  });

  describe('createWallet', () => {
    it('should create a new wallet', async () => {
      const receipt = await factory.createWallet(name, owners, required, {
        from: accounts[0],
      });
      const walletAddress = receipt.logs[0].args.wallet;

      const wallet = await MultiSigWallet.at(walletAddress);

      const count = await wallet.getOwnersCount();
      assert.equal(owners.length, count);

      await Promise.all((
        owners.map(async (owner, index) => {
          const found = await wallet.getOwner(index);
          assert.equal(owner, found);
        })
      ));
    });

    it('should add wallet into list', async () => {
      const oldCount = await factory.getWalletsCount();

      await factory.createWallet(name, owners, required, {
        from: accounts[0],
      });

      const count = await factory.getWalletsCount();

      assert.equal(1, count.sub(oldCount));
    });
  });
});
