import { render, screen, fireEvent } from '@testing-library/react';

import { container } from 'tsyringe';

import CounterThird from './CounterThird';

test('CounterThird (increase 1)', () => {
  container.clearInstances();

  render(<CounterThird />);

  fireEvent.click(screen.getByText('Increase'));

  screen.getByText('1');
});

test('CounterThird (increase 2)', () => {
  container.clearInstances();

  render(<CounterThird />);

  fireEvent.click(screen.getByText('Increase'));
  fireEvent.click(screen.getByText('Increase'));

  screen.getByText('2');
});
