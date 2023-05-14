/* eslint-disable class-methods-use-this */

import { User, Order } from '../types';

import { generateLineItem } from '../utils';

import data from '../data';

type OrderWithUser = Order & {
  user: {
    id: string;
    name: string;
    email: string;
  }
}

function findUser(userId: string): User | undefined {
  return data.users.find((i) => i.id === userId);
}

function userOrders(user?: User): OrderWithUser[] {
  if (!user) {
    return [];
  }

  return user.orders.map((order) => ({
    ...order,
    user: {
      id: user.id,
      name: user.name,
      email: user.email,
    },
  }));
}

function allOrders(): OrderWithUser[] {
  return data.users.reduce((acc, user) => [
    ...acc, ...userOrders(user),
  ], [] as OrderWithUser[]);
}

export default class OrderService {
  list({ userId, admin }: {
    userId?: string;
    admin?: boolean;
  } = {}) {
    const orders = userId
      ? userOrders(findUser(userId))
      : allOrders();

    return orders.map((order) => ({
      id: order.id,
      orderer: admin ? order.user : undefined,
      title: order.title,
      status: order.status,
      totalPrice: order.totalPrice,
      orderedAt: order.orderedAt,
    }));
  }

  find(orderId: string) {
    const order = allOrders().find((i) => i.id === orderId);
    if (!order) {
      throw Error('Not Found');
    }

    return {
      id: order.id,
      orderer: order.user,
      title: order.title,
      lineItems: order.lineItems.map(generateLineItem),
      totalPrice: order.totalPrice,
      receiver: order.receiver,
      payment: order.payment,
      status: order.status,
      orderedAt: order.orderedAt,
    };
  }

  update({ id, status }: {
    id: string;
    status: string;
  }) {
    const orders = data.users
      .reduce((acc, user) => [...acc, ...user.orders], [] as Order[]);

    const order = orders.find((i) => i.id === id);
    if (!order) {
      throw Error('Not Found');
    }

    console.log({ id, order, status });

    order.status = status;
  }
}

export const orderService = new OrderService();
