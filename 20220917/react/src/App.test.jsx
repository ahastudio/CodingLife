import { render, screen } from '@testing-library/react';

import { MemoryRouter } from 'react-router-dom';

import App from './App';

test('App', () => {
  render((
    <MemoryRouter>
      <App />
    </MemoryRouter>
  ));

  screen.getByText(/Hello/);
});
