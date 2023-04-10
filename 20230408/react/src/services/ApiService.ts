import axios from 'axios';

import {
  Category, ProductSummary, ProductDetail, Cart, OrderSummary, OrderDetail,
} from '../types';

const API_BASE_URL = process.env.API_BASE_URL
                     || 'https://shop-demo-api-02.fly.dev';

export default class ApiService {
  private instance = axios.create({
    baseURL: API_BASE_URL,
  });

  private accessToken = '';

  setAccessToken(accessToken: string) {
    if (accessToken === this.accessToken) {
      return;
    }

    const authorization = accessToken ? `Bearer ${accessToken}` : undefined;

    this.instance = axios.create({
      baseURL: API_BASE_URL,
      headers: { Authorization: authorization },
    });

    this.accessToken = accessToken;
  }

  async login({ email, password }: {
    email: string;
    password: string;
  }): Promise<string> {
    const { data } = await this.instance.post('/session', { email, password });
    const { accessToken } = data;
    return accessToken;
  }

  async logout(): Promise<void> {
    await this.instance.delete('/session');
  }

  async signup({ email, name, password }: {
    email: string;
    name: string;
    password: string;
  }): Promise<string> {
    const { data } = await this.instance.post('/users', {
      email, name, password,
    });
    const { accessToken } = data;
    return accessToken;
  }

  async fetchCurrentUser(): Promise<{
    id: string;
    name: string;
  }> {
    const { data } = await this.instance.get('/users/me');
    const { id, name } = data;
    return { id, name };
  }

  async fetchCategories(): Promise<Category[]> {
    const { data } = await this.instance.get('/categories');
    const { categories } = data;
    return categories;
  }

  async fetchProducts({ categoryId }: {
    categoryId?: string;
  } = {}): Promise<ProductSummary[]> {
    const { data } = await this.instance.get('/products', {
      params: { categoryId },
    });
    const { products } = data;
    return products;
  }

  async fetchProduct({ productId }: {
    productId: string;
  }): Promise<ProductDetail> {
    const { data } = await this.instance.get(`/products/${productId}`);
    return data;
  }

  async fetchCart(): Promise<Cart> {
    const { data } = await this.instance.get('/cart');
    return data;
  }

  async addProductToCart({ productId, options, quantity }: {
    productId: string;
    options: {
      id: string;
      itemId: string;
    }[];
    quantity: number;
  }): Promise<void> {
    await this.instance.post('/cart/line-items', {
      productId, options, quantity,
    });
  }

  async fetchOrders(): Promise<OrderSummary[]> {
    const { data } = await this.instance.get('/orders');
    const { orders } = data;
    return orders;
  }

  async fetchOrder({ orderId }: {
    orderId: string;
  }): Promise<OrderDetail> {
    const { data } = await this.instance.get(`/orders/${orderId}`);
    return data;
  }
}

export const apiService = new ApiService();
