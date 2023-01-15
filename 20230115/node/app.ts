import express from 'express';
import cors from 'cors';

const port = 3000;

const { log } = console;

const app = express();

app.use(cors());

app.get('/', (req, res) => {
  res.send('Hello, world!');
});

app.get('/products', (req, res) => {
  const products = [
    {
      category: 'Fruits', price: '$1', stocked: true, name: 'Apple',
    },
    {
      category: 'Fruits', price: '$1', stocked: true, name: 'Dragonfruit',
    },
    {
      category: 'Fruits', price: '$2', stocked: false, name: 'Passionfruit',
    },
    {
      category: 'Vegetables', price: '$2', stocked: true, name: 'Spinach',
    },
    {
      category: 'Vegetables', price: '$4', stocked: false, name: 'Pumpkin',
    },
    {
      category: 'Vegetables', price: '$1', stocked: true, name: 'Peas',
    },
  ];

  res.send({ products });
});

app.listen(port, () => {
  log(`Server running at http://localhost:${port}`);
});
