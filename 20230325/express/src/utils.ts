import { LineItem } from './types';

import data from './data';

export function findUser(authorization = '') {
  const matches = authorization.match(/Bearer (.*)/);
  const accessToken = matches && matches[1];
  const user = data.users.find((i) => i.accessToken === accessToken);
  return user ?? null;
}

export function generateLineItem(lineItem: LineItem) {
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

export function generateCart(orderLineItems: LineItem[]) {
  const lineItems = orderLineItems.map(generateLineItem);

  return {
    lineItems,
    totalPrice: lineItems.map((i) => i.totalPrice).reduce((a, b) => a + b, 0),
  };
}
