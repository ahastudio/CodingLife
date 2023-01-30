import { render, screen, fireEvent } from '@testing-library/react';

import { container } from 'tsyringe';

import CounterSecond from './CounterSecond';

test('CounterSecond (increase 1)', () => {
  container.clearInstances();

  render(<CounterSecond />);

  fireEvent.click(screen.getByText('Increase'));

  screen.getByText('1');
});

test('CounterSecond (increase 2)', () => {
  container.clearInstances();

  render(<CounterSecond />);

  fireEvent.click(screen.getByText('Increase'));
  fireEvent.click(screen.getByText('Increase'));

  screen.getByText('2');
});
