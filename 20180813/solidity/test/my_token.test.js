/* global web3 artifacts contract describe before beforeEach it assert */

const { BigNumber } = web3;

const { advanceBlock } = require('openzeppelin-solidity/test/helpers/advanceToBlock');

const MyToken = artifacts.require('./MyToken.sol');

contract('MyToken', ([owner, account1, account2]) => {
  const AMOUNT = new BigNumber(100);

  before(async () => {
    advanceBlock();
  });

  beforeEach(async () => {
    this.token = await MyToken.new({ from: owner });
    await this.token.transfer(account1, 1000, { from: owner });
  });

  describe('#transfer', () => {
    it('should transfer token to another', async () => {
      const balance1 = await this.token.balanceOf(account1);
      const balance2 = await this.token.balanceOf(account2);

      await this.token.transfer(account2, AMOUNT, {
        from: account1,
      });

      const newBalance1 = await this.token.balanceOf(account1);
      const newBalance2 = await this.token.balanceOf(account2);

      assert.equal(-AMOUNT, newBalance1.minus(balance1));
      assert.equal(+AMOUNT, newBalance2.minus(balance2));
    });
  });

  describe('#transferFrom', () => {
    describe('with #approve', () => {
      it('should transfer token from another', async () => {
        const balance1 = await this.token.balanceOf(account1);
        const balance2 = await this.token.balanceOf(account2);

        await this.token.approve(account2, AMOUNT, {
          from: account1,
        });

        await this.token.transferFrom(account1, account2, AMOUNT, {
          from: account2,
        });

        const newBalance1 = await this.token.balanceOf(account1);
        const newBalance2 = await this.token.balanceOf(account2);

        assert.equal(-AMOUNT, newBalance1.minus(balance1));
        assert.equal(+AMOUNT, newBalance2.minus(balance2));
      });
    });

    describe('without #approve', () => {
      it('should not transfer token from another', async () => {
        try {
          await this.token.transferFrom(account1, account2, AMOUNT, {
            from: account2,
          });
        } catch (e) {
          if (e.message === 'VM Exception while processing transaction: revert') {
            return;
          }
        }

        throw new Error();
      });
    });
  });
});
