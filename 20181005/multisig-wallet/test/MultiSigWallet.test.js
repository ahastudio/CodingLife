/* global artifacts, contract, describe, beforeEach, it, assert, web3 */

const { eth } = web3;

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
      const receipt = await wallet.requestTransaction(target, amount, '', {
        from: from,
      });
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
        assert.equal(txId, pendingTxId.valueOf());
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
    const target = others[0];
    let txId;

    beforeEach(async () => {
      await eth.sendTransaction({
        from: accounts[0], to: wallet.address, value: deposit,
      });

      const receipt = await wallet.requestTransaction(target, amount, '', {
        from: owners[0],
      });
      txId = receipt.logs[0].args.transactionId;
    });

    context('with owner', () => {
      let oldCount;

      beforeEach(async () => {
        oldCount = await wallet.getConfirmationsCount(txId);
      });

      it('should confirm a transaction', async () => {
        await wallet.confirmTransaction(txId, { from: owners[1] });

        const count = await wallet.getConfirmationsCount(txId);
        assert.equal(1, count.minus(oldCount));
      });

      it('should not confirm twice', async () => {
        await wallet.confirmTransaction(txId, { from: owners[0] });

        const count = await wallet.getConfirmationsCount(txId);
        assert.equal(0, count.minus(oldCount));
      });

      context('when more confirmations required', () => {
        it('should increase confirmations count', async () => {
          await wallet.confirmTransaction(txId, { from: owners[1] });
          await wallet.confirmTransaction(txId, { from: owners[2] });

          const count = await wallet.getConfirmationsCount(txId);
          assert.equal(2, count.minus(oldCount));
        });
      });

      context('with enough confirmations', () => {
        it('should remove pending transaction', async () => {
          const oldBalance = await eth.getBalance(target);

          [...Array(required - 1).keys()].forEach(async (i) => {
            await wallet.confirmTransaction(txId, { from: owners[i + 1] });
          });

          const count = await wallet.getPendingsCount();
          assert.equal(0, count.valueOf());

          const balance = await eth.getBalance(target);
          assert.equal(amount, balance.minus(oldBalance).valueOf());
        });

        it('should not confirm executed transaction', async () => {
          [...Array(required - 1).keys()].forEach(async (i) => {
            await wallet.confirmTransaction(txId, { from: owners[i + 1] });
          });

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

      const receipt = await wallet.requestTransaction(target, amount, '', {
        from: owners[0],
      });
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
        assert.equal(1, oldCount.minus(count));
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
