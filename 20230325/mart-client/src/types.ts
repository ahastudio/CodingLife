export type Product = {
  id: number;
  name: string;
  price: number;
}

export type CartItem = {
  id: number;
  product: {
    id: number;
    name: string;
    price: number;
  };
  quantity: number;
  totalPrice: number;
}

export type Cart = {
  cartItems: CartItem[];
  totalPrice: number;
}
