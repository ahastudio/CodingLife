export default class Product {
  id: number;

  name: string;

  price: number;

  constructor({ id, name, price }: {
    id: number;
    name: string;
    price: number;
  }) {
    this.id = id;
    this.name = name;
    this.price = price;
  }
}
