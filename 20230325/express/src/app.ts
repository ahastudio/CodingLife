import { ulid } from 'ulidx';
import jwt from 'jsonwebtoken';
import argon2 from '@node-rs/argon2';

import express from 'express';
import cors from 'cors';
import bodyParser from 'body-parser';

import { cartService } from './services/CartService';

import { User, LineItem } from './types';

import data, { resetUsers } from './data';

const SECRET = 'SECRET';

function findUser(authorization = '') {
  const matches = authorization.match(/Bearer (.*)/);
  const accessToken = matches && matches[1];
  const user = data.users.find((i) => i.accessToken === accessToken);
  return user ?? null;
}

function generateLineItem(lineItem: LineItem) {
  const { product } = lineItem;

  return {
    id: lineItem.id,
    product: {
      id: product.id,
      thumbnail: { url: product.images[0].url },
      name: product.name,
    },
    options: lineItem.options.map((option) => ({
      name: option.name,
      item: {
        name: option.item.name,
      },
    })),
    unitPrice: product.price,
    quantity: lineItem.quantity,
    totalPrice: product.price * lineItem.quantity,
  };
}

function generateCart(orderLineItems: LineItem[]) {
  const lineItems = orderLineItems.map(generateLineItem);

  return {
    lineItems,
    totalPrice: lineItems.map((i) => i.totalPrice).reduce((a, b) => a + b, 0),
  };
}

const app = express();

app.use(cors());
app.use(bodyParser.json());

app.get('/', (req, res) => {
  res.send('Hello, world!');
});

app.post('/session', (req, res) => {
  const { email, password } = req.body;

  const user = data.users.find((i) => i.email === email);

  if (!user || !argon2.verifySync(user.password, password)) {
    res.status(400).send('Bad Request');
    return;
  }

  const accessToken = jwt.sign({ userId: user.id }, SECRET);

  user.accessToken = accessToken;

  res.status(201).send({ accessToken });
});

app.delete('/session', (req, res) => {
  const user = findUser(req.headers.authorization);
  if (!user) {
    res.status(403).send('Forbidden');
    return;
  }

  user.accessToken = '';

  res.send('Deleted');
});

app.get('/users', (req, res) => {
  res.send({
    users: data.users.map((user) => ({
      id: user.id,
      email: user.email,
      cart: generateCart(user.cart.lineItems),
      orders: user.orders.map((order) => generateCart(order.lineItems)),
      accessToken: user.accessToken,
    })),
  });
});

app.post('/users', (req, res) => {
  const email = (req.body.email ?? '').trim();
  const name = (req.body.name ?? '').trim();
  const password = (req.body.password ?? '').trim();

  if (!email || !password) {
    res.status(400).send('Bad Request');
    return;
  }

  const found = data.users.some((i) => i.email === email);
  if (found) {
    res.status(400).send('Email has been already taken');
    return;
  }

  const userId = ulid();
  const accessToken = jwt.sign({ userId }, SECRET);

  const user: User = {
    id: userId,
    email,
    name: name ?? email,
    password: argon2.hashSync(password),
    accessToken,
    cart: {
      lineItems: [],
    },
    orders: [],
  };

  data.users.push(user);

  res.status(201).send({ accessToken });
});

app.get('/users/me', (req, res) => {
  const user = findUser(req.headers.authorization);
  if (!user) {
    res.status(403).send('Forbidden');
    return;
  }

  res.send({
    id: user.id,
    name: user.name,
  });
});

app.get('/categories', (req, res) => {
  res.send({
    categories: data.categories.map((category) => ({
      id: category.id,
      name: category.name,
    })),
  });
});

app.get('/products', (req, res) => {
  const categoryId = req.query.categoryId as string;
  const categoryName = data.categories.find((i) => i.id === categoryId)?.name;

  const products = categoryId
    ? data.products.filter((i) => i.categoryName === categoryName)
    : data.products;

  res.send({
    products: products.map((product) => {
      const category = data.categories
        .find((i) => i.name === product.categoryName);

      return {
        id: product.id,
        category: category && {
          id: category.id,
          name: category.name,
        },
        thumbnail: {
          url: product.images[0].url,
        },
        name: product.name,
        price: product.price,
      };
    }),
  });
});

app.get('/products/:id', (req, res) => {
  const productId = req.params.id as string;

  const [product] = data.products.filter((i) => i.id === productId);
  if (!product) {
    res.status(404).send('Not Found');
    return;
  }

  const category = data.categories
    .find((i) => i.name === product.categoryName);

  res.send({
    id: productId,
    category: category && {
      id: category.id,
      name: category.name,
    },
    images: product.images.map((image) => ({
      url: image.url,
    })),
    name: product.name,
    price: product.price,
    options: product.options,
    description: product.description.trim()
      .split('\n').map((i) => i.trim()).join('\n'),
  });
});

app.get('/cart', (req, res) => {
  const user = findUser(req.headers.authorization);
  if (!user) {
    res.status(403).send('Forbidden');
    return;
  }

  const lineItems = user.cart.lineItems.map((lineItem) => {
    const { product } = lineItem;

    return {
      id: lineItem.id,
      product: {
        id: product.id,
        thumbnail: { url: product.images[0].url },
        name: product.name,
      },
      options: lineItem.options.map((option) => ({
        name: option.name,
        item: {
          name: option.item.name,
        },
      })),
      unitPrice: product.price,
      quantity: lineItem.quantity,
      totalPrice: product.price * lineItem.quantity,
    };
  });

  res.send({
    lineItems,
    totalPrice: lineItems.map((i) => i.totalPrice).reduce((a, b) => a + b, 0),
  });
});

app.post('/cart/line-items', (req, res) => {
  const user = findUser(req.headers.authorization);
  if (!user) {
    res.status(403).send('Forbidden');
    return;
  }

  const { productId, options, quantity } = req.body;

  type Option = {
    id: string;
    itemId: string;
  };

  const optionItems = (options as Option[])
    .reduce((acc, { id, itemId }) => ({
      ...acc,
      [id]: itemId,
    }), {});

  try {
    cartService.addProduct({
      user, productId, optionItems, quantity,
    });

    res.status(201).send('Created');
  } catch (e) {
    console.error(e);
    res.status(400).send('Bad Request');
  }
});

app.get('/orders', (req, res) => {
  const user = findUser(req.headers.authorization);
  if (!user) {
    res.status(403).send('Forbidden');
    return;
  }

  res.send({
    orders: user.orders.map((order) => ({
      id: order.id,
      title: order.title,
      status: order.status,
      totalPrice: order.totalPrice,
      orderedAt: order.orderedAt,
    })),
  });
});

app.get('/orders/:id', (req, res) => {
  const user = findUser(req.headers.authorization);
  if (!user) {
    res.status(403).send('Forbidden');
    return;
  }

  const orderId = req.params.id as string;

  const order = user.orders.find((i) => i.id === orderId);
  if (!order) {
    res.status(404).send('Not Found');
    return;
  }

  res.send({
    id: order.id,
    title: order.title,
    status: order.status,
    lineItems: order.lineItems.map(generateLineItem),
    totalPrice: order.totalPrice,
    orderedAt: order.orderedAt,
  });
});

app.post('/orders', (req, res) => {
  const user = findUser(req.headers.authorization);
  if (!user) {
    res.status(403).send('Forbidden');
    return;
  }

  try {
    cartService.createOrder(user);

    res.status(201).send('Created');
  } catch (e) {
    console.error(e);
    res.status(400).send('Bad Request');
  }
});

app.get('/backdoor/setup-database', (req, res) => {
  resetUsers();

  res.send('OK');
});

export default app;
