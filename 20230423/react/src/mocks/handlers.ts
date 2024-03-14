// eslint-disable-next-line import/no-extraneous-dependencies
import { rest } from 'msw';

import { OrderSummary, ProductSummary } from '../types';

import fixtures from '../../fixtures';

const API_BASE_URL = import.meta.env.API_BASE_URL || 'http://localhost:3000/admin';

const productSummaries: ProductSummary[] = fixtures.products
  .map((product) => ({
    id: product.id,
    category: product.category,
    thumbnail: product.images[0],
    name: product.name,
    price: product.price,
    hidden: false,
  }));

const orderSummaries: OrderSummary[] = [
  {
    id: '0BV000ODR0001',
    orderer: fixtures.users[0],
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
  rest.get(`${API_BASE_URL}/users/me`, (req, res, ctx) => (
    res(ctx.json({ id: 'USER-ID', name: 'tester' }))
  )),
  rest.get(`${API_BASE_URL}/users`, (req, res, ctx) => (
    res(ctx.json({ users: fixtures.users }))
  )),
  rest.get(`${API_BASE_URL}/categories`, (req, res, ctx) => (
    res(ctx.json({ categories: fixtures.categories }))
  )),
  rest.get(`${API_BASE_URL}/categories/:id`, (req, res, ctx) => {
    const category = fixtures.categories.find((i) => i.id === req.params.id);
    if (!category) {
      return res(ctx.status(404));
    }
    return res(ctx.json(category));
  }),
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
  rest.post(`${API_BASE_URL}/orders`, (req, res, ctx) => (
    res(ctx.status(201))
  )),
];

export default handlers;
