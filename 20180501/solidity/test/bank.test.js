/* global artifacts contract it assert */

const Bank = artifacts.require('Bank');
const MyToken = artifacts.require('MyToken');

contract('Bank', ([account]) => {
  const unit = Math.pow(10, 8);
  const amount = 10 * unit;

  it('should save token', async () => {
    const bank = await Bank.deployed();
    const token = await MyToken.deployed();

    await token.approve(bank.address, amount);

    const initialBalance = await token.balanceOf(account);

    // deposit

    await bank.deposit(token.address, amount);

    const balance = await token.balanceOf(account);
    assert.equal(amount, initialBalance.minus(balance));

    // withdraw

    await bank.withdraw();

    const lastBalance = await token.balanceOf(account);
    assert.equal(amount, lastBalance.minus(balance));
  });
});
