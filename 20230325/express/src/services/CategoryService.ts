/* eslint-disable class-methods-use-this */

import { ulid } from 'ulid';

import data from '../data';

export default class CategoryService {
  list({ admin }: {
    admin?: boolean;
  } = {}) {
    const categories = admin
      ? data.categories
      : data.categories.filter((i) => !i.hidden);

    return categories.map((category) => ({
      id: category.id,
      name: category.name,
      hidden: admin ? category.hidden : undefined,
    }));
  }

  find(categoryId: string) {
    const category = data.categories.find((i) => i.id === categoryId);

    if (!category) {
      throw Error('Not Found');
    }

    return category;
  }

  create({ name }: {
    name: string;
  }) {
    if (!name) {
      throw Error('Bad Request');
    }

    const category = {
      id: ulid(),
      name,
      hidden: true,
    };

    data.categories.push(category);
  }

  update({ id, name, hidden }: {
    id: string;
    name: string;
    hidden: boolean;
  }) {
    const category = data.categories.find((i) => i.id === id);

    if (!category) {
      throw Error('Not Found');
    }

    if (!name) {
      throw Error('Bad Request');
    }

    category.name = name;
    category.hidden = hidden;
  }
}

export const categoryService = new CategoryService();
