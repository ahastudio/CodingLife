import TransferStore from './TransferStore';

const context = describe;

describe('TransferStore', () => {
  context('when amount is correct', () => {
    it('updates amount', () => {
      const transferStore = new TransferStore();

      expect(transferStore.amount('a')).toBe(2_000);
      expect(transferStore.amount('b')).toBe(0);

      transferStore.transfer('a', 'b', 1_000);

      expect(transferStore.amount('a')).toBe(1_000);
      expect(transferStore.amount('b')).toBe(1_000);
    });
  });

  context('when amount is too large', () => {
    it('updates amount', () => {
      const transferStore = new TransferStore();

      expect(transferStore.amount('a')).toBe(2_000);
      expect(transferStore.amount('b')).toBe(0);

      transferStore.transfer('a', 'b', 10_000);

      expect(transferStore.amount('a')).toBe(2_000);
      expect(transferStore.amount('b')).toBe(0);
    });
  });
});
