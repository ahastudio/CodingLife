import { container } from 'tsyringe';

import { screen } from '@testing-library/react';

import { render } from '../../test-helpers';

import ProductDetail from './ProductDetail';

import ProductDetailStore from '../../stores/ProductDetailStore';

import fixtures from '../../../fixtures';

const [product] = fixtures.products;

// const { options } = product;

// jest.mock('../../hooks/useProductDetailStore', () => () => [
//   { product },
// ]);

// jest.mock('../../hooks/useProductFormStore', () => () => [
//   {
//     options,
//     selectedOptionItems: options.map((i) => i.items[0]),
//     quantity: 1,
//   },
// ]);

test('ProductDetail', async () => {
  const store = container.resolve(ProductDetailStore);

  await store.fetchProduct({ productId: product.id });

  render(<ProductDetail />);

  screen.getByText(product.name);
});
