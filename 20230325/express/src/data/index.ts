import users from './users';
import categories from './categories';
import products from './products';
import lineItems from './lineItems';

import { User, Product } from '../types';

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

function getCategories() {
  return categories.map((category) => ({ ...category }));
}

function getProducts(): Product[] {
  return products.map((product) => JSON.parse(JSON.stringify(product)));
}

const data = {
  users: getUsers(),
  categories: getCategories(),
  products: getProducts(),
  lineItems,
};

export function resetData() {
  data.users = getUsers();
  data.categories = getCategories();
  data.products = getProducts();
}

export default data;
