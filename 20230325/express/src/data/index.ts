import users from './users';
import categories from './categories';
import products from './products';
import lineItems from './lineItems';

import { User } from '../types';

function getUsers(): User[] {
  return users.map((user) => ({
    ...user,
    accessToken: `ACCESS.TOKEN.${user.id}`,
    cart: {
      ...user.cart,
      lineItems: [...user.cart.lineItems],
    },
    orders: user.orders.map((order) => ({
      ...order,
      lineItems: [...order.lineItems],
    })),
  }));
}

const data = {
  users: getUsers(),
  categories,
  products,
  lineItems,
};

export function resetUsers() {
  data.users = getUsers();
}

export default data;
