import argon2 from '@node-rs/argon2';

import { getProduct, getOrderOptions } from './products';

import { User } from '../types';

const users: User[] = [
  {
    id: '0BV000USR0001',
    email: 'tester@example.com',
    name: 'tester',
    password: argon2.hashSync('password'),
    role: 'ROLE_USER',
    accessToken: '',
    cart: {
      lineItems: [],
    },
    orders: [
      {
        id: '0BV000ODR0001',
        title: `${getProduct('0BV000PRO0001').name} 외 1`,
        lineItems: [
          {
            id: '0BV000CLI0001',
            product: getProduct('0BV000PRO0001'),
            options: getOrderOptions({
              productId: '0BV000PRO0001',
              itemIds: ['0BV000ITEM001', '0BV000ITEM005'],
            }),
            quantity: 2,
          },
          {
            id: '0BV000CLI0002',
            product: getProduct('0BV000PRO0006'),
            options: getOrderOptions({
              productId: '0BV000PRO0006',
              itemIds: ['0BV000ITEM028', '0BV000ITEM031'],
            }),
            quantity: 1,
          },
        ],
        totalPrice: [
          getProduct('0BV000PRO0001').price * 2,
          getProduct('0BV000PRO0006').price * 1,
        ].reduce((a, b) => a + b, 0),
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
        status: 'paid',
        orderedAt: '2023-01-01 00:00:00',
      },
    ],
  },
  {
    id: '0BV000USR0002',
    email: 'admin@example.com',
    name: 'admin',
    password: argon2.hashSync('password'),
    role: 'ROLE_ADMIN',
    accessToken: '',
    cart: {
      lineItems: [],
    },
    orders: [],
  },
  {
    id: '0BV000USR0003',
    email: 'staff@example.com',
    name: 'staff',
    password: argon2.hashSync('password'),
    role: 'ROLE_STAFF',
    accessToken: '',
    cart: {
      lineItems: [],
    },
    orders: [],
  },
];

export default users;
