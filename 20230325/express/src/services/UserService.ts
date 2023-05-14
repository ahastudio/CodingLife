/* eslint-disable class-methods-use-this */

import { ulid } from 'ulid';
import jwt from 'jsonwebtoken';
import argon2 from '@node-rs/argon2';

import { User } from '../types';

import data from '../data';

const SECRET = process.env.JWT_SECRET || 'SECRET';

export default class UserService {
  list() {
    return data.users.map((user) => ({
      id: user.id,
      name: user.name,
      email: user.email,
      role: user.role.replace(/^ROLE_/, ''),
      // cart: generateCart(user.cart.lineItems),
      // orders: user.orders.map((order) => generateCart(order.lineItems)),
      // accessToken: user.accessToken,
    }));
  }

  signup({ email, name, password }: {
    email: string;
    name: string;
    password: string;
  }) {
    if (!email || !password) {
      throw Error('Bad Request');
    }

    const found = data.users.some((i) => i.email === email);
    if (found) {
      throw Error('Email has been already taken');
    }

    const userId = ulid();
    const accessToken = jwt.sign({ userId }, SECRET);

    const user: User = {
      id: userId,
      email,
      name: name ?? email,
      password: argon2.hashSync(password),
      role: 'ROLE_USER',
      accessToken,
      cart: {
        lineItems: [],
      },
      orders: [],
    };

    data.users.push(user);

    return user;
  }
}

export const userService = new UserService();
