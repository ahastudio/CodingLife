import Product from './Product';

export default class CartItem {
  id: number;

  product: Product;

  quantity: number;

  constructor({ id, product, quantity }: {
    id: number;
    product: Product;
    quantity: number;
  }) {
    this.id = id;
    this.product = product;
    this.quantity = quantity;
  }

  changeQuantity(quantity: number): CartItem {
    return new CartItem({
      id: this.id,
      product: this.product,
      quantity,
    });
  }

  totalPrice(): number {
    return this.product.price * this.quantity;
  }
}
