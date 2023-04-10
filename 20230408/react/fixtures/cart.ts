import { Cart } from '../src/types';

const cart: Cart = {
  lineItems: [
    {
      id: 'line-item-01',
      product: {
        id: 'product-01',
        name: '맨투맨',
      },
      options: [
        { name: '컬러', item: { name: 'black' } },
      ],
      unitPrice: 10_000,
      quantity: 1,
      totalPrice: 10_000,
    },
  ],
  totalPrice: 10_000,
};

export default cart;
