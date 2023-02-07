import { render, screen } from '@testing-library/react';

import { createMemoryRouter, RouterProvider } from 'react-router-dom';

import routes from './routes';

const context = describe;

describe('App', () => {
  function renderRouter(path: string) {
    const router = createMemoryRouter(routes, { initialEntries: [path] });
    render(<RouterProvider router={router} />);
  }

  context('when the current path is “/”', () => {
    it('renders the home page', () => {
      renderRouter('/');

      screen.getByText(/Welcome/);
    });
  });

  context('when the current path is “/about”', () => {
    it('renders the about page', () => {
      renderRouter('/about');

      screen.getByText(/This is test/);
    });
  });

  context('when the current path is “/logout”', () => {
    it('redirects to the home page', () => {
      renderRouter('/logout');

      screen.getByText(/Welcome/);
    });
  });
});
