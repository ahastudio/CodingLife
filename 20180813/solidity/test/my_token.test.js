/* global artifacts contract describe beforeEach it assert */

const MyToken = artifacts.require('./MyToken.sol');

contract('MyToken', ([owner, ...accounts]) => {
  const amount = 100;

  beforeEach(async () => {
    this.token = await MyToken.deployed();
    [this.account1, this.account2] = accounts;

    await this.token.transfer(this.account1, 1000, { from: owner });
  });

  describe('transfer', () => {
    it('should transfer token to another', async () => {
      const balance1 = await this.token.balanceOf(this.account1);
      const balance2 = await this.token.balanceOf(this.account2);

      await this.token.transfer(this.account2, amount, {
        from: this.account1,
      });

      const newBalance1 = await this.token.balanceOf(this.account1);
      const newBalance2 = await this.token.balanceOf(this.account2);

      assert.equal(-amount, newBalance1.minus(balance1).valueOf());
      assert.equal(+amount, newBalance2.minus(balance2).valueOf());
    });
  });

  describe('transferFrom', () => {
    it('should not transfer token from another without approve', async () => {
      try {
        await this.token.transferFrom(this.account1, this.account2, amount, {
          from: this.account2,
        });
      } catch (e) {
        if (e.message === 'VM Exception while processing transaction: revert') {
          return;
        }
      }

      throw new Error();
    });

    it('should transfer token from another with approve', async () => {
      const balance1 = await this.token.balanceOf(this.account1);
      const balance2 = await this.token.balanceOf(this.account2);

      await this.token.approve(this.account2, amount, {
        from: this.account1,
      });

      await this.token.transferFrom(this.account1, this.account2, amount, {
        from: this.account2,
      });

      const newBalance1 = await this.token.balanceOf(this.account1);
      const newBalance2 = await this.token.balanceOf(this.account2);

      assert.equal(-amount, newBalance1.minus(balance1).valueOf());
      assert.equal(+amount, newBalance2.minus(balance2).valueOf());
    });
  });
});
