import Item from './Item';

export default class Cart {
  items: Item[];

  price: number;

  constructor({ items = [] }: {
    items?: Item[];
  } = {}) {
    this.items = items;
    this.price = items.reduce((acc, item) => acc + item.price, 0);
  }

  addItem(): Cart {
    const id = this.items.length + 1;
    const item = new Item({
      id,
      title: `Item #${id}`,
      price: 10_000,
    });
    return new Cart({
      items: [...this.items, item],
    });
  }
}
