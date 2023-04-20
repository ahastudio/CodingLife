import ApiService from './ApiService';

import fixtures from '../../fixtures';

const context = describe;

describe('ApiService', () => {
  let apiService: ApiService;

  beforeEach(() => {
    apiService = new ApiService();
  });

  test('setAccessToken', async () => {
    apiService.setAccessToken('ACCESS-TOKEN');
    apiService.setAccessToken('');
    apiService.setAccessToken('ACCESS-TOKEN');
  });

  test('login', async () => {
    const accessToken = await apiService.login({
      email: 'tester@example.com',
      password: 'password',
    });
    expect(accessToken).toBeTruthy();
  });

  test('logout', async () => {
    apiService.setAccessToken('ACCESS-TOKEN');

    await apiService.logout();
  });

  test('signup', async () => {
    const accessToken = await apiService.signup({
      email: 'newbie@example.com',
      name: 'Newbie',
      password: 'password',
    });
    expect(accessToken).toBeTruthy();
  });

  test('fetchCurrentUser', async () => {
    apiService.setAccessToken('ACCESS-TOKEN');

    const user = await apiService.fetchCurrentUser();

    expect(user.id).toBeTruthy();
    expect(user.name).toBeTruthy();
  });

  test('fetchCategories', async () => {
    const categories = await apiService.fetchCategories();
    expect(categories).not.toHaveLength(0);
  });

  describe('fetchProducts', () => {
    context('without category ID', () => {
      it('returns products', async () => {
        const products = await apiService.fetchProducts();
        expect(products).not.toHaveLength(0);
      });
    });

    context('with category ID', () => {
      it('returns products', async () => {
        const products = await apiService.fetchProducts({
          categoryId: fixtures.categories[0].id,
        });
        expect(products).not.toHaveLength(0);
      });
    });
  });

  test('fetchProduct', async () => {
    const product = await apiService.fetchProduct({
      productId: fixtures.products[0].id,
    });
    expect(product.name).toBe(fixtures.products[0].name);
  });

  test('fetchCart', async () => {
    apiService.setAccessToken('ACCESS-TOKEN');

    const cart = await apiService.fetchCart();

    expect(cart.lineItems).not.toHaveLength(0);
  });

  test('addProductToCart', async () => {
    apiService.setAccessToken('ACCESS-TOKEN');

    const [product] = fixtures.products;

    await apiService.addProductToCart({
      productId: product.id,
      options: product.options.map((option) => ({
        id: option.id,
        itemId: option.items[0].id,
      })),
      quantity: 1,
    });
  });

  test('createOrder', async () => {
    apiService.setAccessToken('ACCESS-TOKEN');

    await apiService.createOrder({
      receiver: {
        name: '홍길동',
        address1: '서울특별시 성동구 상원12길 34',
        address2: 'ㅇㅇㅇ호',
        postalCode: '04790',
        phoneNumber: '01012345678',
      },
      payment: {
        merchantId: 'ORDER-20230101-00000001',
        transactionId: '123456789012',
      },
    });
  });

  test('fetchOrders', async () => {
    apiService.setAccessToken('ACCESS-TOKEN');

    const orders = await apiService.fetchOrders();

    expect(orders).toHaveLength(1);
  });

  test('fetchOrder', async () => {
    const [orderData] = fixtures.orders;

    apiService.setAccessToken('ACCESS-TOKEN');

    const order = await apiService.fetchOrder({
      orderId: orderData.id,
    });

    expect(order.id).toEqual(orderData.id);
    expect(order.lineItems).toHaveLength(orderData.lineItems.length);
  });
});
