import { screen } from '@testing-library/react';

import { render } from '../../test-helpers';

import Table from './Table';

import { LineItem } from '../../types';

import fixtures from '../../../fixtures';

const context = describe;

describe('Table', () => {
  function renderTable({ lineItems }: {
    lineItems: LineItem[];
  }) {
    const totalPrice = lineItems
      .map((i) => i.totalPrice)
      .reduce((a, b) => a + b, 0);

    render((
      <Table
        lineItems={lineItems}
        totalPrice={totalPrice}
      />
    ));
  }

  context('when line item exists', () => {
    const { lineItems } = fixtures.cart;

    it('renders line item', () => {
      renderTable({ lineItems });

      screen.getByText(lineItems[0].product.name);
    });
  });

  context("when line item doesn't exists", () => {
    const lineItems: LineItem[] = [];

    it('renders nothing', () => {
      renderTable({ lineItems });

      expect(screen.queryByText(/제품/)).not.toBeInTheDocument();
    });
  });
});
