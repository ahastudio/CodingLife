const MyToken = artifacts.require('MyToken');

contract('MyToken', (accounts) => {
  it('should put all tokens in the first account', async () => {
    const token = await MyToken.deployed();
    const balance = await token.balanceOf.call(accounts[0]);
    assert.equal(1000 * 1000 * Math.pow(10, 8), balance.valueOf())
  });

  it('should transfer token', async () => {
    const token = await MyToken.deployed();

    const amount = 100;

    const balance1 = await token.balanceOf.call(accounts[0]);
    const balance2 = await token.balanceOf.call(accounts[1]);

    await token.transfer(accounts[1], amount, { from: accounts[0] });

    let balance;

    balance = await token.balanceOf.call(accounts[0]);
    assert.equal(amount, balance1.valueOf() - balance.valueOf());

    balance = await token.balanceOf.call(accounts[1]);
    assert.equal(amount, balance.valueOf() - balance2.valueOf());
  });
});
