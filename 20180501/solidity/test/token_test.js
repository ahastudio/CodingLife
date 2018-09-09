/* global artifacts contract it assert */

const MyToken = artifacts.require('MyToken');

contract('MyToken', ([account1, account2]) => {
  const unit = Math.pow(10, 8);
  const amount = 10 * unit;

  it('should put all tokens in the first account', async () => {
    const token = await MyToken.deployed();
    const balance = await token.balanceOf(account1);
    assert.equal(1000 * 1000 * unit, balance.valueOf());
  });

  it('should transfer token', async () => {
    const token = await MyToken.deployed();

    const balance1 = await token.balanceOf(account1);
    const balance2 = await token.balanceOf(account2);

    await token.transfer(account2, amount, { from: account1 });

    const newBalance1 = await token.balanceOf(account1);
    const newBalance2 = await token.balanceOf(account2);

    assert.equal(amount, balance1.minus(newBalance1));
    assert.equal(amount, newBalance2.minus(balance2));
  });
});
