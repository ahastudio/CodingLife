export default class Item {
  id: number;

  title: string;

  price: number;

  constructor({ id, title, price }: {
    id: number;
    title: string;
    price: number;
  }) {
    this.id = id;
    this.title = title;
    this.price = price;
  }
}
