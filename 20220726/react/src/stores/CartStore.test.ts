import CartStore from './CartStore';

describe('CartStore', () => {
  describe('addItem', () => {
    it('adds an item', () => {
      const cartStore = new CartStore();

      expect(cartStore.cart.items).toHaveLength(0);

      cartStore.addItem();

      expect(cartStore.cart.items).toHaveLength(1);
    });
  });
});
