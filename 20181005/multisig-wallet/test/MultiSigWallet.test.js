/* global artifacts, contract, describe, beforeEach, it, assert, web3 */

const { eth, utils: { toBN } } = web3;

const MultiSigWallet = artifacts.require('./MultiSigWallet.sol');

contract('MultiSigWallet', (accounts) => {
  let wallet;

  const name = 'test wallet';
  const owners = [0, 1, 2, 3, 4, 5].map(i => accounts[i]);
  const others = [6, 7, 8, 9].map(i => accounts[i]);
  const required = 4;
  const deposit = 100;
  const amount = 10;

  beforeEach(async () => {
    wallet = await MultiSigWallet.new(name, owners, required, {
      from: owners[0],
    });
  });

  describe('deposit', () => {
    it('should store ether', async () => {
      await eth.sendTransaction({
        from: accounts[0], to: wallet.address, value: deposit,
      });

      const balance = await eth.getBalance(wallet.address);

      assert.equal(deposit, balance);
    });
  });

  describe('requestTransaction', () => {
    const target = others[0];

    beforeEach(async () => {
      await eth.sendTransaction({
        from: accounts[0], to: wallet.address, value: deposit,
      });
    });

    const request = async (from) => {
      const receipt = await wallet.requestTransaction(
        target, amount, web3.utils.asciiToHex(''), { from: from },
      );
      return receipt.logs[0].args.transactionId;
    };

    context('with owner', () => {
      it('should be confirmed by self', async () => {
        const txId = await request(owners[0]);
        assert(txId > 0);

        const count = await wallet.getConfirmationsCount(txId);
        assert.equal(1, count.valueOf());
      });

      it('should add pending transaction', async () => {
        const txId = await request(owners[0]);
        assert(txId > 0);

        const count = await wallet.getPendingsCount();
        assert.equal(1, count.valueOf());

        const pendingTxId = await wallet.getPendingTransactionId(0);
        assert.equal(txId.toString(), pendingTxId.toString());
      });
    });

    context('without owner', () => {
      it('should be failed', async () => {
        try {
          const txId = await request(others[0]);
          assert(false);
        } catch (e) {
          assert(e.message.includes('revert'), e.message);
        }
      });
    });
  });

  describe('confirmTransaction', () => {
    const requester = owners[0];
    const otherOwners = owners.filter(i => i != requester);
    const target = others[0];
    let txId;

    beforeEach(async () => {
      await eth.sendTransaction({
        from: requester, to: wallet.address, value: deposit,
      });

      const receipt = await wallet.requestTransaction(
        target, amount, web3.utils.asciiToHex(''), { from: owners[0] },
      );
      txId = receipt.logs[0].args.transactionId;
    });

    context('with owner', () => {
      let oldCount;

      beforeEach(async () => {
        oldCount = await wallet.getConfirmationsCount(txId);
      });

      it('should not confirm by myself', async () => {
        await wallet.confirmTransaction(txId, { from: requester });

        const count = await wallet.getConfirmationsCount(txId);
        assert.equal(0, count.sub(oldCount));
      });

      it('should confirm a transaction', async () => {
        await wallet.confirmTransaction(txId, { from: otherOwners[0] });

        const count = await wallet.getConfirmationsCount(txId);
        assert.equal(1, count.sub(oldCount));
      });

      it('should not confirm twice', async () => {
        await wallet.confirmTransaction(txId, { from: otherOwners[0] });
        await wallet.confirmTransaction(txId, { from: otherOwners[0] });

        const count = await wallet.getConfirmationsCount(txId);
        assert.equal(1, count.sub(oldCount));
      });

      context('when more confirmations required', () => {
        it('should increase confirmations count', async () => {
          await wallet.confirmTransaction(txId, { from: otherOwners[0] });
          await wallet.confirmTransaction(txId, { from: otherOwners[1] });

          const count = await wallet.getConfirmationsCount(txId);
          assert.equal(2, count.sub(oldCount));
        });
      });

      context('with enough confirmations', () => {
        it('should remove pending transaction', async () => {
          const oldBalance = await eth.getBalance(target);

          await Promise.all((
            [...Array(required - 1).keys()].map(async (i) => {
              await wallet.confirmTransaction(txId, { from: otherOwners[i] });
            })
          ));

          const count = await wallet.getPendingsCount();
          assert.equal(0, count.valueOf());

          const balance = await eth.getBalance(target);
          assert.equal(amount, toBN(balance).sub(toBN(oldBalance)));
        });

        it('should not confirm executed transaction', async () => {
          await Promise.all((
            [...Array(required - 1).keys()].map(async (i) => {
              await wallet.confirmTransaction(txId, { from: otherOwners[i] });
            })
          ));

          try {
            await wallet.confirmTransaction(txId, { from: owners[required] });
            assert(false);
          } catch (e) {
            assert(e.message.includes('revert'), e.message);
          }
        });
      });
    });

    context('without owner', () => {
      it('should be failed', async () => {
        try {
          await wallet.confirmTransaction(txId, { from: others[1] });
          assert(false);
        } catch (e) {
          assert(e.message.includes('revert'), e.message);
        }
      });
    });
  });

  describe('revokeTransaction', () => {
    const target = others[0];
    let txId;

    beforeEach(async () => {
      await eth.sendTransaction({
        from: accounts[0], to: wallet.address, value: deposit,
      });

      const receipt = await wallet.requestTransaction(
        target, amount, web3.utils.asciiToHex(''), { from: owners[0] },
      );
      txId = receipt.logs[0].args.transactionId;

      await wallet.confirmTransaction(txId, { from: owners[1] });
    });

    context('with sender', () => {
      it('should remove pending transaction', async () => {
        await wallet.revokeTransaction(txId, { from: owners[0] });

        const count = await wallet.getPendingsCount();
        assert.equal(0, count.valueOf());
      });
    });

    context('with owner', () => {
      let oldCount;

      beforeEach(async () => {
        oldCount = await wallet.getConfirmationsCount(txId);
      });

      it('should decrease confirmation count', async () => {
        await wallet.revokeTransaction(txId, { from: owners[1] });

        const count = await wallet.getConfirmationsCount(txId);
        assert.equal(1, oldCount.sub(count));
      });
    });

    context('without owner', () => {
      it('should be failed', async () => {
        try {
          await wallet.revokeTransaction(txId, { from: others[1] });
          assert(false);
        } catch (e) {
          assert(e.message.includes('revert'), e.message);
        }
      });
    });
  });
});
