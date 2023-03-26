import CartItem from './CartItem';

import fixtures from '../fixtures';

describe('Cart', () => {
  const [product] = fixtures.products;

  describe('changeQuantity', () => {
    it('변경 수량에 맞는 새 인스턴스를 돌려준다', () => {
      const cartItem = new CartItem({ id: 1, product, quantity: 1 });

      expect(cartItem.changeQuantity(3))
        .toEqual(new CartItem({ id: 1, product, quantity: 3 }));
    });
  });

  describe('totalPrice', () => {
    it('제품에 대한 총 가격을 돌려준다', () => {
      const cartItem = new CartItem({ id: 1, product, quantity: 3 });

      expect(cartItem.totalPrice()).toBe(product.price * 3);
    });
  });
});
