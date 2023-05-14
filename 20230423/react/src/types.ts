export type User = {
  id: string;
  name: string;
  email: string;
  role: string;
}

export type Category = {
  id: string;
  name: string;
  hidden?: boolean;
}

export type Image = {
  id?: string;
  url: string;
}

export type ProductSummary = {
  id: string;
  category: Category;
  thumbnail: Image;
  name: string;
  price: number;
  hidden: boolean;
}

export type ProductOptionItem = {
  id?: string;
  name: string;
};

export type ProductOption = {
  id?: string;
  name: string;
  items: ProductOptionItem[];
};

export type ProductDetail = {
  id: string;
  category: Category;
  images: Image[];
  name: string;
  price: number;
  options: ProductOption[];
  description: string;
  hidden: boolean;
}

export type OrderOptionItem = {
  name: string;
};

export type OrderOption = {
  name: string;
  item: OrderOptionItem;
};

export type LineItem = {
  id: string;
  product: {
    id: string;
    name: string;
  };
  options: OrderOption[];
  unitPrice: number;
  quantity: number;
  totalPrice: number;
}

export type OrderSummary = {
  id: string;
  orderer: User;
  title: string;
  totalPrice: number;
  status: string;
  orderedAt: string;
}

type Receiver = {
  name: string;
  address1: string;
  address2: string;
  postalCode: string;
  phoneNumber: string;
}

type Payment = {
  merchantId: string;
  transactionId: string;
}

export type OrderDetail = {
  id: string;
  orderer: User;
  title: string;
  lineItems: LineItem[];
  totalPrice: number;
  receiver: Receiver;
  payment: Payment;
  status: string;
  orderedAt: string;
}
