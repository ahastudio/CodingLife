import app from '../app';

import { sessionService } from '../services/SessionService';
import { userService } from '../services/UserService';
import { categoryService } from '../services/CategoryService';
import { productService } from '../services/ProductService';
import { cartService } from '../services/CartService';
import { orderService } from '../services/OrderService';

import { findUser } from '../utils';

app.get('/', (req, res) => {
  res.send('Hello, world!');
});

app.post('/session', (req, res) => {
  const { email, password } = req.body;

  try {
    const { accessToken } = sessionService.login({ email, password });

    res.status(201).send({ accessToken });
  } catch {
    res.status(400).send('Bad Request');
  }
});

app.delete('/session', (req, res) => {
  const user = findUser(req.headers.authorization);
  if (!user) {
    res.status(403).send('Forbidden');
    return;
  }

  sessionService.logout(user);

  res.send('Deleted');
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

app.post('/users', (req, res) => {
  const email = (req.body.email ?? '').trim();
  const name = (req.body.name ?? '').trim();
  const password = (req.body.password ?? '').trim();

  try {
    const user = userService.signup({ email, name, password });

    res.status(201).send({ accessToken: user.accessToken });
  } catch {
    res.status(400).send('Bad Request');
  }
});

app.get('/categories', (req, res) => {
  res.send({
    categories: categoryService.list(),
  });
});

app.get('/products', (req, res) => {
  const categoryId = req.query.categoryId as string;

  const products = productService.list({ categoryId });

  res.send({
    products,
  });
});

app.get('/products/:id', (req, res) => {
  const productId = req.params.id as string;

  try {
    const product = productService.find({ productId });

    res.send({
      ...product,
      hidden: undefined,
    });
  } catch {
    res.status(404).send('Not Found');
  }
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

  const orders = orderService.list({ userId: user.id });

  res.send({ orders });
});

app.get('/orders/:id', (req, res) => {
  const user = findUser(req.headers.authorization);
  if (!user) {
    res.status(403).send('Forbidden');
    return;
  }

  const orderId = req.params.id as string;

  try {
    const order = orderService.find(orderId);

    res.send({
      ...order,
      user: undefined,
    });
  } catch {
    res.status(404).send('Not Found');
  }
});

app.post('/orders', (req, res) => {
  const user = findUser(req.headers.authorization);
  if (!user) {
    res.status(403).send('Forbidden');
    return;
  }

  const { receiver, payment } = req.body;

  try {
    cartService.createOrder({ user, receiver, payment });

    res.status(201).send('Created');
  } catch (e) {
    console.error(e);
    res.status(400).send('Bad Request');
  }
});
