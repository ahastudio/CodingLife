import Store from './Store';

import Item from '../models/Item';
import Cart from '../models/Cart';

export type CartStoreSnapshot = {
  items: Item[];
}

export default class CartStore extends Store<CartStoreSnapshot> {
  private cart = new Cart();

  constructor() {
    super();
    this.takeSnapshot();
  }

  addItem({ productId, quantity }: {
    productId: number;
    quantity: number;
  }) {
    this.cart = this.cart.addItem({ productId, quantity });

    this.update();
  }

  update() {
    this.takeSnapshot();
    this.publish();
  }

  takeSnapshot() {
    this.snapshot = {
      items: this.cart.items,
    };
  }
}
