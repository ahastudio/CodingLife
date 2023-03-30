import { screen } from '@testing-library/react';

import { render } from '../test-helpers';

import Header from './Header';

jest.mock('../hooks/useFetchCategories', () => () => ({
  categories: [],
}));

test('Header', () => {
  render(<Header />);

  screen.getByText(/Shop/);
  screen.getByRole('link', { name: 'Home' });
});
