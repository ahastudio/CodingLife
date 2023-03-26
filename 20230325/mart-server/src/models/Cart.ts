import CartItem from './CartItem';
import Product from './Product';

let newItemId = 0;

export default class Cart {
  cartItems: CartItem[] = [];

  totalPrice = 0;

  addProduct({ product, quantity }: {
    product: Product;
    quantity: number;
  }) {
    this.addItem({ product, quantity });

    this.updateTotalPrice();
  }

  addItem({ product, quantity }: {
    product: Product;
    quantity: number;
  }) {
    const index = this.cartItems
      .findIndex((cartItem) => cartItem.product.id === product.id);

    if (index < 0) {
      this.insertCartItem({ product, quantity });
      return;
    }

    this.updateCartItem({ index, change: quantity });
  }

  insertCartItem({ product, quantity }: {
    product: Product;
    quantity: number;
  }) {
    newItemId += 1;

    const cartItem = new CartItem({ id: newItemId, product, quantity });
    this.cartItems.push(cartItem);
  }

  updateCartItem({ index, change }: {
    index: number;
    change: number;
  }) {
    const cartItem = this.cartItems[index];

    const newCartItem = new CartItem({
      id: cartItem.id,
      product: cartItem.product,
      quantity: cartItem.quantity + change,
    });

    this.cartItems = [
      ...this.cartItems.slice(0, index),
      newCartItem,
      ...this.cartItems.slice(index + 1),
    ];
  }

  updateTotalPrice() {
    this.totalPrice = this.cartItems
      .map((cartItem) => cartItem.totalPrice())
      .reduce((a, b) => a + b);
  }
}
