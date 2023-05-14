/* eslint-disable class-methods-use-this */
/* eslint-disable no-param-reassign */

import jwt from 'jsonwebtoken';
import argon2 from '@node-rs/argon2';

import { User } from '../types';

import data from '../data';

const SECRET = process.env.JWT_SECRET || 'SECRET';

export default class SessionService {
  login({ email, password }: {
    email: string;
    password: string;
  }) {
    const user = data.users.find((i) => i.email === email);

    if (!user || !argon2.verifySync(user.password, password)) {
      throw Error('Login failed');
    }

    const accessToken = jwt.sign({ userId: user.id }, SECRET);

    user.accessToken = accessToken;

    return { user, accessToken };
  }

  logout(user: User) {
    user.accessToken = '';
  }
}

export const sessionService = new SessionService();
