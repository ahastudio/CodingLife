import express from 'express';
import cors from 'cors';
import bodyParser from 'body-parser';

import { cartService } from './services/CartService';

import data from './data';

const app = express();

app.use(cors());
app.use(bodyParser.json());

app.get('/', (req, res) => {
  res.send('Hello, world!');
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
  const lineItems = data.lineItems.map((lineItem) => {
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
    cartService.addProduct({ productId, optionItems, quantity });

    res.status(201).send('Created');
  } catch (e) {
    console.error(e);
    res.status(400).send('Bad Request');
  }
});

app.get('/backdoor/setup-database', (req, res) => {
  data.lineItems.length = 0;

  res.send('OK');
});

export default app;
