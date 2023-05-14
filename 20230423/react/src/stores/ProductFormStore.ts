import { singleton } from 'tsyringe';

import { Store, Action } from 'usestore-ts';

import { apiService } from '../services/ApiService';

import {
  Category, Image, ProductDetail, ProductOption,
} from '../types';

import { append, remove, update } from '../utils';

@singleton()
@Store()
export default class ProductFormStore {
  productId = '';

  category: Category | null = null;

  images: Image[] = [];

  name = '';

  price = '';

  options: ProductOption[] = [];

  description = '';

  hidden = false;

  error = false;

  done = false;

  get valid() {
    const price = parseInt(this.price, 10);
    return !!this.category?.id
      && this.images.length && this.images.every((i) => i.url)
      && !!this.name.trim() && Number.isInteger(price)
      && this.options.every((option) => (
        option.name && option.items.length
          && option.items.every((item) => item.name)
      ))
      && this.description.trim();
  }

  @Action()
  reset() {
    this.productId = '';
    this.category = null;
    this.images = [{ url: '' }];
    this.name = '';
    this.price = '';
    this.options = [];
    this.description = '';
    this.error = false;
    this.done = false;
  }

  @Action()
  setProduct(product: ProductDetail) {
    this.productId = product.id;
    this.category = product.category;
    this.images = product.images;
    this.name = product.name;
    this.price = product.price.toString();
    this.options = product.options;
    this.description = product.description;
    this.hidden = product.hidden;
    this.error = false;
    this.done = false;
  }

  @Action()
  changeCategory(category: Category) {
    this.category = category;
  }

  @Action()
  addImage() {
    this.images = append(this.images, { url: '' });
  }

  @Action()
  removeImage(index: number) {
    this.images = remove(this.images, index);
  }

  @Action()
  changeImageUrl(index: number, url: string) {
    this.images = update(this.images, index, (image) => ({
      ...image,
      url,
    }));
  }

  @Action()
  changeName(name: string) {
    this.name = name;
  }

  @Action()
  changePrice(price: string) {
    this.price = price;
  }

  @Action()
  addOption() {
    const option = {
      name: '',
      items: [{ name: '' }],
    };

    this.options = append(this.options, option);
  }

  @Action()
  removeOption(index: number) {
    this.options = remove(this.options, index);
  }

  @Action()
  changeOptionName(index: number, name: string) {
    this.options = update(this.options, index, (option) => ({
      ...option,
      name,
    }));
  }

  @Action()
  addOptionItem(optionIndex: number) {
    this.options = update(this.options, optionIndex, (option) => ({
      ...option,
      items: append(option.items, { name: '' }),
    }));
  }

  @Action()
  removeOptionItem(optionIndex: number, itemIndex: number) {
    this.options = update(this.options, optionIndex, (option) => ({
      ...option,
      items: remove(option.items, itemIndex),
    }));
  }

  @Action()
  changeOptionItemName(optionIndex: number, itemIndex: number, name: string) {
    this.options = update(this.options, optionIndex, (option) => ({
      ...option,
      items: update(option.items, itemIndex, (item) => ({
        ...item,
        name,
      })),
    }));
  }

  @Action()
  changeDescription(description: string) {
    this.description = description;
  }

  @Action()
  toggleHidden() {
    this.hidden = !this.hidden;
  }

  @Action()
  private setError() {
    this.error = true;
  }

  @Action()
  private setDone() {
    this.done = true;
  }

  async create() {
    try {
      await apiService.createProduct({
        categoryId: this.category?.id || '',
        images: this.images,
        name: this.name,
        price: parseInt(this.price, 10),
        options: this.options,
        description: this.description,
      });

      this.setDone();
    } catch (e) {
      this.setError();
    }
  }

  async update() {
    try {
      await apiService.updateProduct({
        productId: this.productId,
        categoryId: this.category?.id || '',
        images: this.images,
        name: this.name,
        price: parseInt(this.price, 10),
        options: this.options,
        description: this.description,
      });

      this.setDone();
    } catch (e) {
      this.setError();
    }
  }
}
