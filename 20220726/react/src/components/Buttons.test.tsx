import { container } from 'tsyringe';

import { render, screen, fireEvent } from '@testing-library/react';

import Buttons from './Buttons';

import CounterStore from '../stores/CounterStore';
import CartStore from '../stores/CartStore';

const counterStore = container.resolve(CounterStore);
const cartStore = container.resolve(CartStore);

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
