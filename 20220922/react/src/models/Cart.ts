import Item from './Item';

export default class Cart {
  readonly items: Item[] = [];

  constructor({ items = [] }: {
    items?: Item[];
  } = {}) {
    this.items = items;
  }

  addItem({ productId, quantity }: {
    productId: number;
    quantity: number;
  }): Cart {
    const index = this.items.findIndex((i) => i.productId === productId);
    return index < 0
      ? this.insertItem({ productId, quantity })
      : this.updateItem({ index, change: quantity });
  }

  private insertItem({ productId, quantity }: {
    productId: number;
    quantity: number;
  }): Cart {
    const id = Math.max(0, ...this.items.map((i) => i.id)) + 1;
    const item = new Item({ id, productId, quantity });
    return new Cart({
      items: [...this.items, item],
    });
  }

  private updateItem({ index, change }: {
    index: number;
    change: number;
  }): Cart {
    const item = this.items[index];
    return new Cart({
      items: [
        ...this.items.slice(0, index),
        new Item({ ...item, quantity: item.quantity + change }),
        ...this.items.slice(index + 1),
      ],
    });
  }
}
