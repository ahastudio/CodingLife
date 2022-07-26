import { render, screen, act } from '@testing-library/react';

import { cartStore } from '../hooks/useCartStore';

import Cart from './Cart';

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
