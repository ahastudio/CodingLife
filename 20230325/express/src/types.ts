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
  images: [{ url: string }];
  name: string;
  price: number;
  options: ProductOption[];
  description: string;
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
