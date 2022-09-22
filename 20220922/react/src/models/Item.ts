export default class Item {
  readonly id: number;

  readonly productId: number;

  readonly quantity: number;

  constructor({ id, productId, quantity }: {
    id: number;
    productId: number;
    quantity: number;
  }) {
    this.id = id;
    this.productId = productId;
    this.quantity = quantity;
  }
}
