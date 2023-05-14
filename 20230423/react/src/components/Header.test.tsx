import { screen } from '@testing-library/react';

import { render } from '../test-helpers';

import Header from './Header';

const accessToken = '';

jest.mock('../hooks/useAccessToken', () => () => ({
  get accessToken() {
    return accessToken;
  },
}));

test('Header', () => {
  render(<Header />);

  screen.getByText(/Shop Admin/);
  screen.getByRole('link', { name: 'Home' });
});
