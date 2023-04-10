// eslint-disable-next-line import/no-extraneous-dependencies
import { rest } from 'msw';

import { OrderSummary, ProductSummary } from '../types';

import fixtures from '../../fixtures';

const API_BASE_URL = process.env.API_BASE_URL
                     || 'https://shop-demo-api-02.fly.dev';

const productSummaries: ProductSummary[] = fixtures.products
  .map((product) => ({
    id: product.id,
    category: product.category,
    thumbnail: product.images[0],
    name: product.name,
    price: product.price,
  }));

const orderSummaries: OrderSummary[] = [
  {
    id: '0BV000ODR0001',
    orderedAt: '2023-01-01 00:00:00',
    status: 'paid',
    title: 'CBCL 하트자수맨투맨 외 1',
    totalPrice: 653000,
  },
];

const handlers = [
  rest.post(`${API_BASE_URL}/session`, (req, res, ctx) => (
    res(
      ctx.status(201),
      ctx.json({ accessToken: 'Header.Payload.Signature' }),
    )
  )),
  rest.delete(`${API_BASE_URL}/session`, (req, res, ctx) => (
    res(ctx.json({}))
  )),
  rest.post(`${API_BASE_URL}/users`, (req, res, ctx) => (
    res(
      ctx.status(201),
      ctx.json({ accessToken: 'Header.Payload.Signature' }),
    )
  )),
  rest.get(`${API_BASE_URL}/users/me`, (req, res, ctx) => (
    res(ctx.json({ id: 'USER-ID', name: 'tester' }))
  )),
  rest.get(`${API_BASE_URL}/categories`, (req, res, ctx) => (
    res(ctx.json({ categories: fixtures.categories }))
  )),
  rest.get(`${API_BASE_URL}/products`, (req, res, ctx) => (
    res(ctx.json({ products: productSummaries }))
  )),
  rest.get(`${API_BASE_URL}/products/:id`, (req, res, ctx) => {
    const product = fixtures.products.find((i) => i.id === req.params.id);
    if (!product) {
      return res(ctx.status(404));
    }
    return res(ctx.json(product));
  }),
  rest.get(`${API_BASE_URL}/cart`, (req, res, ctx) => (
    res(ctx.json(fixtures.cart))
  )),
  rest.post(`${API_BASE_URL}/cart/line-items`, (req, res, ctx) => (
    res(ctx.status(201))
  )),
  rest.get(`${API_BASE_URL}/orders`, (req, res, ctx) => (
    res(ctx.json({ orders: orderSummaries }))
  )),
  rest.get(`${API_BASE_URL}/orders/:id`, (req, res, ctx) => {
    const order = fixtures.orders.find((i) => i.id === req.params.id);
    if (!order) {
      return res(ctx.status(404));
    }
    return res(ctx.json(order));
  }),
];

export default handlers;
