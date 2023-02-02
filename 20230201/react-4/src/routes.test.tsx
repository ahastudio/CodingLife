import { render, screen } from '@testing-library/react';

import { createMemoryRouter, RouterProvider } from 'react-router-dom';

import routes from './routes';

const context = describe;

describe('routes', () => {
  function renderRouter(path: string) {
    const router = createMemoryRouter(routes, { initialEntries: [path] });
    render(<RouterProvider router={router} />);
  }

  context('when the current page is "/"', () => {
    it('renders the home page', () => {
      renderRouter('/');

      screen.getByText(/Welcome/);
    });
  });

  context('when the current page is "/products"', () => {
    it('renders the product list page', () => {
      renderRouter('/products');

      screen.getByText(/Product list/);
    });
  });

  context('when the current page is "/about"', () => {
    it('renders the about page', () => {
      renderRouter('/about');

      screen.getByText(/About Demo App/);
    });
  });

  context('when the current page is "/login"', () => {
    it('renders the login page', () => {
      renderRouter('/login');

      screen.getByText(/Username/);
    });
  });

  context('when the current page is "/logout"', () => {
    it('redirects to the home page', () => {
      renderRouter('/logout');

      screen.getByText(/Welcome/);
    });
  });
});
