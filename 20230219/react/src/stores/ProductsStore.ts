import { singleton } from 'tsyringe';

import { Store, Action } from 'usestore-ts';

import { apiService } from '../services/ApiService';

import { ProductSummary } from '../types';

@singleton()
@Store()
export default class ProductsStore {
  products: ProductSummary[] = [];

  async fetchProducts({ categoryId }: {
    categoryId?: string;
  }) {
    this.setProducts([]);

    const products = await apiService.fetchProducts({ categoryId });

    this.setProducts(products);
  }

  @Action()
  setProducts(products: ProductSummary[]) {
    this.products = products;
  }
}
