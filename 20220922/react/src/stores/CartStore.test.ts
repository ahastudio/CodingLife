import CartStore from './CartStore';

import Item from '../models/Item';

test('CartStore', () => {
  const cartStore = new CartStore();

  const handleChange = jest.fn();

  cartStore.addListener(handleChange);

  cartStore.addItem({ productId: 1, quantity: 2 });

  expect(handleChange).toBeCalled();

  expect(cartStore.getSnapshot()).toEqual({
    items: [
      new Item({ id: 1, productId: 1, quantity: 2 }),
    ],
  });
});
