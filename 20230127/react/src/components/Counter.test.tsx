import { render, screen, fireEvent } from '@testing-library/react';

import { container } from 'tsyringe';

import Store from '../stores/Store';

import Counter from './Counter';

test('Counter', () => {
  container.clearInstances();

  const store = container.resolve(Store);

  render(<Counter />);

  fireEvent.click(screen.getByText('Press'));

  expect(store.state.count).toBe(1);
});
