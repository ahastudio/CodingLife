test('test', () => {
  //
});

// import ApiService from './ApiService';

// import fixtures from '../../fixtures';

// const context = describe;

// describe('ApiService', () => {
//   let apiService: ApiService;

//   beforeEach(() => {
//     apiService = new ApiService();
//   });

//   test('fetchCategories', async () => {
//     const categories = await apiService.fetchCategories();
//     expect(categories).not.toHaveLength(0);
//   });

//   describe('fetchProducts', () => {
//     context('without category ID', () => {
//       it('returns products', async () => {
//         const products = await apiService.fetchProducts();
//         expect(products).not.toHaveLength(0);
//       });
//     });

//     context('with category ID', () => {
//       it('returns products', async () => {
//         const products = await apiService.fetchProducts({
//           categoryId: fixtures.categories[0].id,
//         });
//         expect(products).not.toHaveLength(0);
//       });
//     });
//   });

//   test('fetchProduct', async () => {
//     const product = await apiService.fetchProduct({
//       productId: fixtures.products[0].id,
//     });
//     expect(product.name).toBe(fixtures.products[0].name);
//   });

//   test('fetchCart', async () => {
//     const cart = await apiService.fetchCart();
//     expect(cart.lineItems).not.toHaveLength(0);
//   });

//   test('addProductToCart', async () => {
//     const [product] = fixtures.products;

//     await apiService.addProductToCart({
//       productId: product.id,
//       options: product.options.map((option) => ({
//         id: option.id,
//         itemId: option.items[0].id,
//       })),
//       quantity: 1,
//     });
//   });
// });
