import { render, screen, fireEvent } from '@testing-library/react';

import { counterStore } from '../hooks/useCounterStore';
import { cartStore } from '../hooks/useCartStore';

import Buttons from './Buttons';

const context = describe;

describe('Buttons', () => {
  context('when the “Increase” button is clicked', () => {
    it('updates count', () => {
      render(<Buttons />);

      const oldCount = counterStore.count;

      fireEvent.click(screen.getByText('Increase'));

      const newCount = counterStore.count;

      expect(newCount).toBe(oldCount + 1);
    });
  });

  context('when the “Add product” button is clicked', () => {
    it('updates cart', () => {
      render(<Buttons />);

      const oldCount = cartStore.cart.items.length;

      fireEvent.click(screen.getByText('Add product'));

      const newCount = cartStore.cart.items.length;

      expect(newCount).toBe(oldCount + 1);
    });
  });
});
