import Cart from './Cart';

import fixtures from '../fixtures';

const context = describe;

describe('Cart', () => {
  describe('addProduct', () => {
    const [product] = fixtures.products;

    let cart: Cart;

    beforeEach(() => {
      cart = new Cart();
    });

    context('장바구니에 처음 담는 상품일 때', () => {
      it('appends a new item', () => {
        cart.addProduct({ product, quantity: 1 });

        expect(cart.cartItems).toHaveLength(1);
      });

      it('updates total price', () => {
        cart.addProduct({ product, quantity: 2 });

        expect(cart.totalPrice).toBe(2_000);
      });
    });

    context('이미 장바구니에 담긴 상품일 때', () => {
      beforeEach(() => {
        cart.addProduct({ product, quantity: 1 });
      });

      it('appends a new item', () => {
        cart.addProduct({ product, quantity: 2 });

        expect(cart.cartItems).toHaveLength(1);
      });

      it('updates total price', () => {
        cart.addProduct({ product, quantity: 2 });

        expect(cart.totalPrice).toBe(3_000);
      });
    });
  });
});
