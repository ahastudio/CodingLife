import products from './products';

import { OrderDetail } from '../src/types';

const orders: OrderDetail[] = [
  {
    id: 'order-01',
    title: products[0].name,
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
    status: 'paid',
    totalPrice: 10_000,
    orderedAt: '2023-01-01 00:00:00',
  },
];

export default orders;
