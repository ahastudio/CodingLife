import { ulid } from 'ulid';

import { User, Product, LineItem } from '../types';

import data from '../data';

export default class CartService {
  products: Product[];

  constructor() {
    this.products = data.products;
  }

  addProduct({
    user, productId, optionItems, quantity,
  }: {
    user: User,
    productId: string;
    optionItems: Record<string, string>;
    quantity: number;
  }) {
    const product = this.products.find((i) => i.id === productId);
    if (!product) {
      throw Error('Product Not Found');
    }

    const found = user.cart.lineItems.find((lineItem) => (
      lineItem.product.id === productId
        && lineItem.options.every((i) => i.item.id === optionItems[i.id])
    ));

    if (found) {
      found.quantity += quantity;
      return;
    }

    const lineItem: LineItem = {
      id: ulid(),
      product,
      options: product.options.map((option) => {
        const item = option.items.find((i) => i.id === optionItems[option.id]);
        if (!item) {
          throw Error('Option Item Not Found');
        }
        return {
          id: option.id,
          name: option.name,
          item,
        };
      }),
      quantity,
    };

    user.cart.lineItems.push(lineItem);

    console.log(JSON.stringify(lineItem, null, '  '));
  }
}

export const cartService = new CartService();
