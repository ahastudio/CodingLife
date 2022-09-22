import Cart from './Cart';
import Item from './Item';

const context = describe;

describe('Cart', () => {
  let cart: Cart;

  beforeEach(() => {
    cart = new Cart();
  });

  describe('addItem', () => {
    context('when there is no same product', () => {
      it('adds an item', () => {
        const productIds = [1, 2, 3];

        const newCart = productIds.reduce((prevCart, productId) => (
          prevCart.addItem({ productId, quantity: 2 })
        ), cart);

        expect(newCart.items).toHaveLength(productIds.length);

        newCart.items.forEach((item, index) => {
          expect(item.id).toBe(index + 1);
          expect(item.productId).toBe(productIds[index]);
          expect(item.quantity).toBe(2);
        });
      });
    });

    context('when there is the same product', () => {
      it('adds an item', () => {
        const productId = 1;
        const quantities = [1, 2, 3];

        const newCart = quantities.reduce((prevCart, quantity) => (
          prevCart.addItem({ productId, quantity })
        ), cart);

        expect(newCart.items).toHaveLength(1);

        expect(newCart.items).toEqual([
          new Item({
            id: 1,
            productId,
            quantity: quantities.reduce((a, b) => a + b),
          }),
        ]);
      });
    });
  });
});
