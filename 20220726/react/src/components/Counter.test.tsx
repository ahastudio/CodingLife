import { container } from 'tsyringe';

import { render, screen, act } from '@testing-library/react';

import Counter from './Counter';

import CounterStore from '../stores/CounterStore';

const counterStore = container.resolve(CounterStore);

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
