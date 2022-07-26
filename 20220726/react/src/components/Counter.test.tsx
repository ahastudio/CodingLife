import { render, screen, act } from '@testing-library/react';

import { counterStore } from '../hooks/useCounterStore';

import Counter from './Counter';

describe('Counter', () => {
  it('renders count', () => {
    render(<Counter />);

    screen.getByText('Count: 0');

    act(() => {
      counterStore.increase();
    });

    screen.getByText('Count: 1');
  });
});
