// import products from './products';

import {
  // Product,
  // OrderOption,
  LineItem,
} from '../types';

// function options(product: Product): OrderOption[] {
//   return product.options.map((option) => {
//     const item = option.items[0];
//     return {
//       id: option.id,
//       name: option.name,
//       item: { id: item.id, name: item.name },
//     };
//   });
// }

const lineItems: LineItem[] = [
  // {
  //   id: '0BV000CLI0001',
  //   product: products[0],
  //   options: options(products[0]),
  //   quantity: 2,
  // },
  // {
  //   id: '0BV000CLI0002',
  //   product: products[1],
  //   options: options(products[1]),
  //   quantity: 1,
  // },
];

export default lineItems;
