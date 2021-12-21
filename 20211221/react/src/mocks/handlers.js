// eslint-disable-next-line import/no-extraneous-dependencies
import { rest } from 'msw';

const BASE_URL = 'https://eatgo-customer-api.ahastudio.com';

let error = false;

const handlers = [
  rest.get(`${BASE_URL}/restaurants`, (req, res, ctx) => {
    if (error) {
      return res(ctx.status(500));
    }

    const restaurants = [
      {
        id: 1, categoryId: 1, name: '양천주가', address: '서울 강남구',
      },
    ];

    return res(
      ctx.status(200),
      ctx.json(restaurants),
    );
  }),
];

export function setError() {
  error = true;
}

export function clearError() {
  error = false;
}

export default handlers;
