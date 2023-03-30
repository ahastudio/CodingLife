import { container as iocContainer } from 'tsyringe';

import { render } from '../../../test-helpers';

import Price from './Price';

import ProductFormStore from '../../../stores/ProductFormStore';

import numberFormat from '../../../utils/numberFormat';

import fixtures from '../../../../fixtures';

const [product] = fixtures.products;

describe('Price', () => {
  const quantity = 2;

  beforeEach(() => {
    const productFormStore = iocContainer.resolve(ProductFormStore);

    productFormStore.setProduct(product);
    productFormStore.changeQuantity(quantity);
  });

  it('renders price as formatted number', () => {
    const { container } = render(<Price />);

    const price = numberFormat(product.price * quantity);

    expect(container).toHaveTextContent(`${price}원`);
  });
});
