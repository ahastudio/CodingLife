import { container } from 'tsyringe';

import { render, screen, act } from '@testing-library/react';

import Cart from './Cart';

import CartStore from '../stores/CartStore';

const cartStore = container.resolve(CartStore);

describe('Cart', () => {
  it('renders total price and items', () => {
    render(<Cart />);

    screen.getByText('Total price: 0원');

    act(() => {
      cartStore.addItem();
      cartStore.addItem();
    });

    screen.getByText('Total price: 20,000원');
    screen.getByText('Item #1');
    screen.getByText('Item #2');
  });
});
