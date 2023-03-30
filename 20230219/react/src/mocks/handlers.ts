// eslint-disable-next-line import/no-extraneous-dependencies
import { rest } from 'msw';

import { ProductSummary } from '../types';

import fixtures from '../../fixtures';

const API_BASE_URL = process.env.API_BASE_URL || 'http://localhost:3000';

const productSummaries: ProductSummary[] = fixtures.products
  .map((product) => ({
    id: product.id,
    category: product.category,
    thumbnail: product.images[0],
    name: product.name,
    price: product.price,
  }));

const handlers = [
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
];

export default handlers;
