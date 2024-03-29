/* eslint-disable class-methods-use-this */

import { ulid } from 'ulid';

import {
  User, Order, LineItem, Receiver, Payment,
} from '../types';

import data from '../data';

export default class CartService {
  addProduct({
    user, productId, optionItems, quantity,
  }: {
    user: User,
    productId: string;
    optionItems: Record<string, string>;
    quantity: number;
  }) {
    const product = data.products.find((i) => i.id === productId);
    if (!product) {
      throw Error('Product Not Found');
    }

    const found = user.cart.lineItems.find((lineItem) => (
      lineItem.product.id === productId
        && lineItem.options.every((i) => i.item.id === optionItems[i.id])
    ));

    if (found) {
      found.quantity += quantity;
      return;
    }

    const lineItem: LineItem = {
      id: ulid(),
      product,
      options: product.options.map((option) => {
        const item = option.items.find((i) => i.id === optionItems[option.id]);
        if (!item) {
          throw Error('Option Item Not Found');
        }
        return {
          id: option.id,
          name: option.name,
          item,
        };
      }),
      quantity,
    };

    user.cart.lineItems.push(lineItem);

    console.log(JSON.stringify(lineItem, null, '  '));
  }

  createOrder({ user, receiver, payment }: {
    user: User;
    receiver: Receiver;
    payment: Payment;
  }) {
    const { cart } = user;
    const { lineItems } = cart;

    if (!lineItems.length) {
      return;
    }

    const title = lineItems[0].product.name + (
      lineItems.length > 2 ? ` 외 ${lineItems.length - 1}` : ''
    );

    const totalPrice = lineItems.reduce((acc, lineItem) => (
      acc + lineItem.product.price * lineItem.quantity
    ), 0);

    const now = new Date().toISOString();

    const order: Order = {
      id: ulid(),
      title,
      lineItems,
      totalPrice,
      receiver,
      payment,
      status: 'paid',
      orderedAt: now.replace('T', ' ').slice(0, now.indexOf('.')),
    };

    user.orders.push(order);

    cart.lineItems = [];
  }
}

export const cartService = new CartService();
