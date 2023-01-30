import { render, screen, fireEvent } from '@testing-library/react';

import { container } from 'tsyringe';

import CounterFirst from './CounterFirst';

test('CounterFirst (increase 1)', () => {
  container.clearInstances();

  render(<CounterFirst />);

  fireEvent.click(screen.getByText('Increase'));

  screen.getByText('1');
});

test('CounterFirst (increase 2)', () => {
  container.clearInstances();

  render(<CounterFirst />);

  fireEvent.click(screen.getByText('Increase'));
  fireEvent.click(screen.getByText('Increase'));

  screen.getByText('2');
});
