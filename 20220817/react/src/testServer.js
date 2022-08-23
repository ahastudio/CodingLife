/* eslint-disable import/no-extraneous-dependencies */

import { rest } from 'msw';
import { setupServer } from 'msw/node';

import config from './config';

const baseUrl = config.apiBaseUrl;

const server = setupServer(
  rest.post(`${baseUrl}/session`, async (req, res, ctx) => {
    const { accountNumber, password } = await req.json();
    if (accountNumber === '1234' && password === 'password') {
      return res(ctx.json({
        accessToken: 'ACCESS.TOKEN',
        name: 'Tester',
        amount: 100_000,
      }));
    }
    return res(ctx.status(400));
  }),
);

export default server;
