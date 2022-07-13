import { render, screen } from '@testing-library/react';

import { StoreProvider } from '../wrappers';

import Counter from './Counter';

describe('Counter', () => {
  it('renders number', async () => {
    render(<Counter />, { wrapper: StoreProvider });

    screen.getByText(/Count: 0/);
  });
});
