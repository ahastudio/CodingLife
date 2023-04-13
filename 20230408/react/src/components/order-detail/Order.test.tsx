import { screen } from '@testing-library/react';

import { render } from '../../test-helpers';

import Order from './Order';

import { OrderDetail } from '../../types';

import fixtures from '../../../fixtures';

const context = describe;

describe('Order', () => {
  context('when order is empty', () => {
    const order: OrderDetail = {
      ...fixtures.orders[0],
      lineItems: [],
      totalPrice: 0,
    };

    it('renders nothing', () => {
      const { container } = render(<Order order={order} />);

      expect(container).toBeEmptyDOMElement();
    });
  });

  context('when order has line items', () => {
    const [order] = fixtures.orders;

    it('renders a line items', () => {
      render(<Order order={order} />);

      screen.getByText(order.lineItems[0].product.name);
    });
  });
});
