import { singleton } from 'tsyringe';

import { Store, Action } from 'usestore-ts';

import { apiService } from '../services/ApiService';

import { ProductDetail, ProductOption, ProductOptionItem } from '../types';

@singleton()
@Store()
export default class ProductFormStore {
  productId = '';

  options: ProductOption[] = [];

  selectedOptionItems: ProductOptionItem[] = [];

  quantity = 1;

  done = false;

  @Action()
  setProduct(product: ProductDetail) {
    this.productId = product.id;
    this.options = product.options;
    this.selectedOptionItems = this.options.map((i) => i.items[0]);
    this.quantity = 1;
    this.done = false;
  }

  @Action()
  changeOptionItem({ optionId, optionItemId }: {
    optionId: string;
    optionItemId: string;
  }) {
    this.selectedOptionItems = this.options.map((option, index) => (
      option.id !== optionId
        ? this.selectedOptionItems[index]
        : option.items.find((i) => i.id === optionItemId) ?? option.items[0]
    ));
  }

  @Action()
  changeQuantity(quantity: number) {
    if (quantity <= 0) {
      return;
    }
    if (quantity > 10) {
      return;
    }
    this.quantity = quantity;
  }

  async addToCart() {
    this.resetDone();

    await apiService.addProductToCart({
      productId: this.productId,
      options: this.options.map((option, index) => ({
        id: option.id,
        itemId: this.selectedOptionItems[index].id,
      })),
      quantity: this.quantity,
    });

    this.complete();
  }

  @Action()
  resetDone() {
    this.done = false;
  }

  @Action()
  complete() {
    this.quantity = 1;
    this.done = true;
  }
}
