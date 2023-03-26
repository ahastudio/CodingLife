import express from 'express';
import cors from 'cors';
import bodyParser from 'body-parser';

import Product from './models/Product';
import Cart from './models/Cart';

const products = [
  new Product({ id: 1, name: '제품 #1', price: 100_000 }),
  new Product({ id: 2, name: '제품 #2', price: 150_000 }),
];

const cart = new Cart();

const app = express();

app.use(cors());
app.use(bodyParser.json());

app.get('/products', (req, res) => {
  res.send(products);
});

app.get('/cart-items', (req, res) => {
  res.send({
    cartItems: cart.cartItems.map((cartItem) => ({
      id: cartItem.id,
      product: {
        id: cartItem.product.id,
        name: cartItem.product.name,
        price: cartItem.product.price,
      },
      quantity: cartItem.quantity,
      totalPrice: cartItem.totalPrice(),
    })),
    totalPrice: cart.totalPrice,
  });
});

app.post('/cart-items', (req, res) => {
  const { productId } = req.body;

  const product = products.find((i) => i.id === productId);
  if (!product) {
    res.status(400).send('Bad Request');
    return;
  }

  cart.addProduct({ product, quantity: 1 });

  res.status(200).send('');
});

export default app;
