import { Request } from 'express';

import app from '../app';

import { sessionService } from '../services/SessionService';
import { userService } from '../services/UserService';
import { categoryService } from '../services/CategoryService';
import { productService } from '../services/ProductService';
import { orderService } from '../services/OrderService';

import { findUser } from '../utils';

function adminRequired(req: Request) {
  const user = findUser(req.headers.authorization);
  if (user?.role !== 'ROLE_ADMIN') {
    throw Error('Forbidden');
  }

  return user;
}

app.post('/admin/session', (req, res) => {
  const { email, password } = req.body;

  try {
    const { user, accessToken } = sessionService.login({ email, password });

    if (user.role !== 'ROLE_ADMIN') {
      throw Error('Admin required');
    }

    res.status(201).send({ accessToken });
  } catch {
    res.status(400).send('Bad Request');
  }
});

app.delete('/admin/session', (req, res) => {
  const user = adminRequired(req);

  user.accessToken = '';

  res.send('Deleted');
});

app.get('/admin/users/me', (req, res) => {
  const user = adminRequired(req);

  res.send({
    id: user.id,
    name: user.name,
  });
});

app.get('/admin/users', (req, res) => {
  adminRequired(req);

  res.send({
    users: userService.list(),
  });
});

app.get('/admin/categories', (req, res) => {
  adminRequired(req);

  res.send({
    categories: categoryService.list({ admin: true }),
  });
});

app.get('/admin/categories/:id', (req, res) => {
  adminRequired(req);

  const categoryId = req.params.id as string;

  try {
    const category = categoryService.find(categoryId);

    res.send(category);
  } catch {
    res.status(404).send('Not Found');
  }
});

app.post('/admin/categories', (req, res) => {
  adminRequired(req);

  const name = (req.body.name || '').trim();

  try {
    categoryService.create({ name });

    res.status(201).send('OK');
  } catch {
    res.status(400).send('Bad Request');
  }
});

app.patch('/admin/categories/:id', (req, res) => {
  adminRequired(req);

  const id = req.params.id as string;
  const name = (req.body.name || '').trim();
  const hidden = req.body.hidden || false;

  try {
    categoryService.update({ id, name, hidden });

    res.send('OK');
  } catch {
    res.status(400).send('Bad Request');
  }
});

app.get('/admin/products', (req, res) => {
  adminRequired(req);

  res.send({
    products: productService.list({ admin: true }),
  });
});

app.get('/admin/products/:id', (req, res) => {
  adminRequired(req);

  const productId = req.params.id as string;

  try {
    const product = productService.find({ productId, admin: true });

    res.send(product);
  } catch {
    res.status(404).send('Not Found');
  }
});

app.post('/admin/products', (req, res) => {
  adminRequired(req);

  const productDto = req.body;

  try {
    productService.create({ productDto });

    res.status(201).send('OK');
  } catch {
    res.status(400).send('Bad Request');
  }
});

app.patch('/admin/products/:id', (req, res) => {
  adminRequired(req);

  const productId = req.params.id as string;
  const productDto = req.body;

  try {
    productService.update({ productId, productDto });

    res.send('OK');
  } catch {
    res.status(400).send('Bad Request');
  }
});

app.get('/admin/orders', (req, res) => {
  adminRequired(req);

  const orders = orderService.list({ admin: true });

  res.send({ orders });
});

app.get('/admin/orders/:id', (req, res) => {
  adminRequired(req);

  const orderId = req.params.id as string;

  try {
    const order = orderService.find(orderId);

    res.send(order);
  } catch {
    res.status(404).send('Not Found');
  }
});

app.patch('/admin/orders/:id', (req, res) => {
  adminRequired(req);

  const id = req.params.id as string;
  const { status } = req.body;

  try {
    const order = orderService.update({ id, status });

    res.send(order);
  } catch {
    res.status(400).send('Bad Request');
  }
});
