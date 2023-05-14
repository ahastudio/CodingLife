import request from 'supertest';

import app from '../app';

import './admin';

import { User } from '../types';

import data, { resetData } from '../data';

const context = describe;

let currentUser: User;
let accessToken: string;

beforeEach(() => {
  resetData();

  // eslint-disable-next-line @typescript-eslint/no-non-null-assertion
  currentUser = data.users.find((i) => i.role === 'ROLE_ADMIN')!;

  accessToken = `ACCESS.TOKEN.${currentUser.id}`;
});

test('POST /admin/session', async () => {
  const response = await request(app)
    .post('/admin/session')
    .send({
      email: 'admin@example.com',
      password: 'password',
    });

  expect(response.status).toBe(201);
});

test('DELETE /admin/session', async () => {
  const response = await request(app)
    .delete('/admin/session')
    .set('Authorization', `Bearer ${accessToken}`);

  expect(response.status).toBe(200);
});

test('GET /admin/users/me', async () => {
  const response = await request(app)
    .get('/admin/users/me')
    .set('Authorization', `Bearer ${accessToken}`);

  expect(response.status).toBe(200);
  expect(response.body.id).toBeTruthy();
});

test('GET /admin/users', async () => {
  const response = await request(app)
    .get('/admin/users')
    .set('Authorization', `Bearer ${accessToken}`);

  expect(response.status).toBe(200);
  expect(response.body.users).toBeTruthy();
});

test('GET /admin/categories', async () => {
  const response = await request(app)
    .get('/admin/categories')
    .set('Authorization', `Bearer ${accessToken}`);

  expect(response.status).toBe(200);
  expect(response.body.categories).toBeTruthy();
});

test('GET /admin/categories/:id', async () => {
  const [category] = data.categories;

  const response = await request(app)
    .get(`/admin/categories/${category.id}`)
    .set('Authorization', `Bearer ${accessToken}`);

  expect(response.status).toBe(200);
  expect(response.body.id).toBe(category.id);
});

describe('POST /admin/categories', () => {
  context('with valid attributes', () => {
    it('responds with 201', async () => {
      const response = await request(app)
        .post('/admin/categories')
        .send({
          name: 'Test',
        })
        .set('Authorization', `Bearer ${accessToken}`);

      expect(response.status).toBe(201);
    });
  });

  context('with invalid attributes', () => {
    it('responds with 400', async () => {
      const response = await request(app)
        .post('/admin/categories')
        .send({
          name: '   ',
        })
        .set('Authorization', `Bearer ${accessToken}`);

      expect(response.status).toBe(400);
    });
  });
});

describe('PATCH /admin/categories/:id', () => {
  const [category] = data.categories;

  context('with valid attributes', () => {
    it('responds with 201', async () => {
      const response = await request(app)
        .patch(`/admin/categories/${category.id}`)
        .send({
          name: 'Test',
        })
        .set('Authorization', `Bearer ${accessToken}`);

      expect(response.status).toBe(200);
    });
  });

  context('with invalid attributes', () => {
    it('responds with 400', async () => {
      const response = await request(app)
        .patch(`/admin/categories/${category.id}`)
        .send({
          name: '   ',
        })
        .set('Authorization', `Bearer ${accessToken}`);

      expect(response.status).toBe(400);
    });
  });

  context('when category not found', () => {
    it('responds with 400', async () => {
      const response = await request(app)
        .patch('/admin/categories/xxx')
        .send({
          name: 'Test',
        })
        .set('Authorization', `Bearer ${accessToken}`);

      // TODO: respond with 404 status code
      expect(response.status).toBe(400);
    });
  });
});

describe('GET /admin/products', () => {
  it('responds with 200', async () => {
    const response = await request(app)
      .get('/admin/products')
      .set('Authorization', `Bearer ${accessToken}`);

    expect(response.status).toBe(200);
  });
});

describe('GET /admin/products/:id', () => {
  const [product] = data.products;

  context('when product exists', () => {
    it('responds with 200', async () => {
      const response = await request(app)
        .get(`/admin/products/${product.id}`)
        .set('Authorization', `Bearer ${accessToken}`);

      expect(response.status).toBe(200);
    });
  });

  context('when product not found', () => {
    it('responds with 404', async () => {
      const response = await request(app)
        .get('/admin/products/xxx')
        .set('Authorization', `Bearer ${accessToken}`);

      expect(response.status).toBe(404);
    });
  });
});

describe('POST /admin/products', () => {
  context('when valid attributes', () => {
    it('responds with 201', async () => {
      const oldCount = data.products.length;

      const response = await request(app)
        .post('/admin/products')
        .send({
          categoryId: data.categories[0].id,
          images: [
            { url: 'https://example.com/products/01.jpg' },
          ],
          name: '맨투맨',
          price: 128_000,
          options: [
            {
              name: '컬러',
              items: [
                { name: 'black' },
                { name: 'white' },
              ],
            },
            {
              name: '사이즈',
              items: [
                { name: 'S' },
                { name: 'M' },
              ],
            },
          ],
          description: '편하게 입을 수 있는 맨투맨',
        })
        .set('Authorization', `Bearer ${accessToken}`);

      expect(response.status).toBe(201);

      const newCount = data.products.length;

      expect(newCount).toBe(oldCount + 1);
    });
  });

  context('when invalid attributes', () => {
    it('responds with 400', async () => {
      const response = await request(app)
        .post('/admin/products')
        .send({
          categoryId: data.categories[0].id,
          images: [],
          name: '  ',
          price: 128_000,
          options: [],
          description: '',
        })
        .set('Authorization', `Bearer ${accessToken}`);

      expect(response.status).toBe(400);
    });
  });
});

describe('PATCH /admin/products/{id}', () => {
  const [product] = data.products;

  context('when valid attributes', () => {
    it('responds with 201', async () => {
      const response = await request(app)
        .patch(`/admin/products/${product.id}`)
        .send({
          categoryId: data.categories[0].id,
          images: [
            { url: 'https://example.com/products/10.jpg' },
          ],
          name: '맨투맨',
          price: 128_000,
          options: [
            {
              name: '컬러',
              items: [
                { name: 'black' },
                { name: 'white' },
              ],
            },
            {
              name: '사이즈',
              items: [
                { name: 'S' },
                { name: 'M' },
              ],
            },
          ],
          description: '편하게 입을 수 있는 맨투맨',
        })
        .set('Authorization', `Bearer ${accessToken}`);

      expect(response.status).toBe(200);
    });
  });

  context('when invalid attributes', () => {
    it('responds with 400', async () => {
      const response = await request(app)
        .patch(`/admin/products/${product.id}`)
        .send({
          categoryId: data.categories[0].id,
          images: [],
          name: '  ',
          price: 128_000,
          options: [],
          description: '',
        })
        .set('Authorization', `Bearer ${accessToken}`);

      expect(response.status).toBe(400);
    });
  });
});
