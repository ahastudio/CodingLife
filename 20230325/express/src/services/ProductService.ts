/* eslint-disable class-methods-use-this */

import { ulid } from 'ulid';

import { Product } from '../types';

import data from '../data';

type ProductDto = {
  categoryId: string;
  images: {
    id?: string;
    url: string;
  }[];
  name: string;
  price: number;
  options: {
    id?: string;
    name: string;
    items: {
      id?: string;
      name: string;
    }[];
  }[];
  description: string;
  hidden?: boolean;
};

export default class ProductService {
  list({ categoryId, admin }: {
    categoryId?: string;
    admin?: boolean;
  } = {}) {
    const categoryName = data.categories.find((i) => i.id === categoryId)?.name;

    const products = categoryId
      ? data.products.filter((i) => i.categoryName === categoryName)
      : data.products;

    return products.map((product) => {
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
        hidden: admin ? product.hidden : undefined,
      };
    });
  }

  find({ productId, admin }: {
    productId: string;
    admin?: boolean;
  }) {
    const [product] = data.products.filter((i) => i.id === productId);
    if (!product) {
      throw Error('Not Found');
    }

    const category = data.categories
      .find((i) => i.name === product.categoryName);

    return {
      id: productId,
      category: category && {
        id: category.id,
        name: category.name,
      },
      images: product.images.map((image) => ({
        id: admin ? image.id : undefined,
        url: image.url,
      })),
      name: product.name,
      price: product.price,
      options: product.options,
      description: product.description.trim()
        .split('\n').map((i) => i.trim()).join('\n'),
      hidden: product.hidden,
    };
  }

  create({ productDto }: {
    productDto: ProductDto;
  }) {
    const name = productDto.name.trim();
    if (!name) {
      throw Error('Bad request');
    }

    if (productDto.price < 0) {
      throw Error('Bad request');
    }

    const category = data.categories
      .find((i) => i.id === productDto.categoryId);
    if (!category) {
      throw Error('Category not found');
    }

    const product: Product = {
      id: ulid(),
      categoryName: category.name,
      images: productDto.images.map((i) => ({
        id: ulid(),
        url: i.url,
      })),
      name,
      price: productDto.price,
      options: productDto.options.map((option) => ({
        id: ulid(),
        name: option.name,
        items: option.items.map((item) => ({
          id: ulid(),
          name: item.name,
        })),
      })),
      description: productDto.description,
      hidden: true,
    };

    data.products.push(product);
  }

  update({ productId, productDto }: {
    productId: string;
    productDto: ProductDto;
  }) {
    const [product] = data.products.filter((i) => i.id === productId);
    if (!product) {
      throw Error('Not Found');
    }

    const name = productDto.name.trim();
    if (!name) {
      throw Error('Bad request');
    }

    if (productDto.price < 0) {
      throw Error('Bad request');
    }

    const category = data.categories
      .find((i) => i.id === productDto.categoryId);
    if (!category) {
      throw Error('Category not found');
    }

    product.categoryName = category.name;
    product.images = productDto.images.map((i) => ({
      id: i.id ?? ulid(),
      url: i.url,
    }));
    product.name = name;
    product.price = productDto.price;
    product.options = productDto.options.map((option) => ({
      id: option.id ?? ulid(),
      name: option.name,
      items: option.items.map((item) => ({
        id: item.id ?? ulid(),
        name: item.name,
      })),
    }));
    product.description = productDto.description;
    product.hidden = !!product.hidden;

    data.products.push(product);
  }
}

export const productService = new ProductService();
