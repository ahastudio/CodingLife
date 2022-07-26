import { singleton } from 'tsyringe';

import { Store, Action } from 'usestore-ts';

import Cart from '../models/Cart';

@singleton()
@Store()
export default class CartStore {
  cart = new Cart();

  @Action()
  addItem() {
    this.cart = this.cart.addItem();
  }
}
