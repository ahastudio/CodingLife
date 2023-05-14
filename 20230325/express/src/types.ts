export type ProductOptionItem = {
  id: string;
  name: string;
};

export type ProductOption = {
  id: string;
  name: string;
  items: ProductOptionItem[];
};

export type Product = {
  id: string;
  categoryName: string;
  images: {
    id?: string;
    url: string;
  }[];
  name: string;
  price: number;
  options: ProductOption[];
  description: string;
  hidden: boolean;
};

export type OrderOptionItem = {
  id: string;
  name: string;
};

export type OrderOption = {
  id: string;
  name: string;
  item: OrderOptionItem;
};

export type LineItem = {
  id: string;
  product: Product;
  options: OrderOption[];
  quantity: number;
};

export type Cart = {
  lineItems: LineItem[];
};

export type Receiver = {
  name: string;
  address1: string;
  address2: string;
  postalCode: string;
  phoneNumber: string;
}

export type Payment = {
  merchantId: string;
  transactionId: string;
}

export type Order = {
  id: string;
  title: string;
  lineItems: LineItem[];
  totalPrice: number;
  receiver: Receiver;
  payment: Payment;
  status: string;
  orderedAt: string;
};

export type User = {
  id: string;
  email: string;
  name: string;
  password: string;
  accessToken: string;
  role: string;
  cart: Cart;
  orders: Order[];
};
