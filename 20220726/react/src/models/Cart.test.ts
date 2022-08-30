import Cart from './Cart';

describe('Cart', () => {
  describe('addItem', () => {
    let cart: Cart;

    beforeEach(() => {
      cart = new Cart();
    });

    it('adds an item', () => {
      expect(cart.items).toHaveLength(0);
      expect(cart.price).toBe(0);

      cart = cart.addItem();

      expect(cart.items).toHaveLength(1);
      expect(cart.price).toBe(cart.items[0].price);
    });
  });
});
