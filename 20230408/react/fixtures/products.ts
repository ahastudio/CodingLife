import { ProductDetail } from '../src/types';

const products: ProductDetail[] = [
  {
    id: 'product-01',
    category: {
      id: 'category-01',
      name: 'Top',
    },
    images: [
      { url: 'http://example.com/01.jpg' },
    ],
    name: 'Product #1',
    price: 128_000,
    options: [
      {
        id: 'option-01',
        name: 'Color',
        items: [
          { id: 'option-item-01', name: 'Black' },
          { id: 'option-item-02', name: 'White' },
        ],
      },
      {
        id: 'option-02',
        name: 'Size',
        items: [
          { id: 'option-item-03', name: 'S' },
          { id: 'option-item-04', name: 'M' },
          { id: 'option-item-05', name: 'L' },
        ],
      },
    ],
    description: '1st line\n2nd line\n3rd line',
  },
];

export default products;
