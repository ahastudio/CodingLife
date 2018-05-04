const Bank = artifacts.require('Bank');
const MyToken = artifacts.require('MyToken');

contract('Bank', (accounts) => {
  it('should save token', async () => {
    const bank = await Bank.deployed();
    const token = await MyToken.deployed();
    const account = accounts[0];

    const amount = 100;

    await token.approve(bank.address, amount);

    let oldBalance;
    let newBalance;

    // deposite

    oldBalance = await token.balanceOf.call(account);

    await bank.deposite(token.address, amount)

    newBalance = await token.balanceOf.call(account);
    assert.equal(amount, oldBalance.valueOf() - newBalance.valueOf());

    // withdraw

    oldBalance = newBalance;

    await bank.withdraw()

    newBalance = await token.balanceOf.call(account);
    assert.equal(amount, newBalance.valueOf() - oldBalance.valueOf());
  });
});
