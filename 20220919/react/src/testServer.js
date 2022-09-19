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
  rest.get(`${baseUrl}/accounts/me`, async (req, res, ctx) => res(ctx.json({
    name: 'Tester',
    accountNumber: '1234',
    amount: 100_000,
  }))),
  rest.get(`${baseUrl}/transactions`, async (req, res, ctx) => res(ctx.json({
    transactions: [
      {
        id: 1, activity: '송금', name: '5678', amount: 3_000,
      },
    ],
  }))),
  rest.post(`${baseUrl}/transactions`, async (req, res, ctx) => {
    const { amount } = await req.json();
    if (amount <= 0) {
      return res(
        ctx.status(400),
        ctx.json({
          code: 1002,
          message: '금액이 잘못됐습니다',
        }),
      );
    }
    return res(ctx.status(200));
  }),
);

export default server;
