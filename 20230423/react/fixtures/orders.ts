import users from './users';
import products from './products';

import { OrderDetail } from '../src/types';

const orders: OrderDetail[] = [
  {
    id: 'order-01',
    orderer: users[0],
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
    receiver: {
      name: '홍길동',
      address1: '서울특별시 성동구 상원12길 34',
      address2: 'ㅇㅇㅇ호',
      postalCode: '04790',
      phoneNumber: '01012345678',
    },
    payment: {
      merchantId: 'ORDER-20230101-00000001',
      transactionId: '123456789012',
    },
    orderedAt: '2023-01-01 00:00:00',
  },
];

export default orders;
