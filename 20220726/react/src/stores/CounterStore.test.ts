import CounterStore from './CounterStore';

describe('CounterStore', () => {
  describe('addItem', () => {
    it('updates count', () => {
      const counterStore = new CounterStore();

      expect(counterStore.count).toBe(0);

      counterStore.increase();

      expect(counterStore.count).toBe(1);
    });
  });

  describe('reset', () => {
    it('sets count to zero', () => {
      const counterStore = new CounterStore();

      counterStore.increase();

      counterStore.reset();

      expect(counterStore.count).toBe(0);
    });
  });
});
