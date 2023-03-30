import { screen } from '@testing-library/react';

import { render } from '../../test-helpers';

import CartView from './CartView';

import fixtures from '../../../fixtures';

const context = describe;

describe('CartView', () => {
  context('when the cart is empty', () => {
    const cart = {
      lineItems: [],
      totalPrice: 0,
    };

    it('renders the empty message', () => {
      render(<CartView cart={cart} />);

      screen.getByText(/비었습니다/);
    });
  });

  context('when the cart has a line item', () => {
    const { cart } = fixtures;

    it('renders a line item', () => {
      render(<CartView cart={cart} />);

      screen.getByText(cart.lineItems[0].product.name);
    });
  });
});
