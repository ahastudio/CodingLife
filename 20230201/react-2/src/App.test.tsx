import { render, screen } from '@testing-library/react';

import { MemoryRouter } from 'react-router-dom';

import App from './App';

const context = describe;

describe('App', () => {
  function renderApp(path: string) {
    render((
      <MemoryRouter initialEntries={[path]}>
        <App />
      </MemoryRouter>
    ));
  }

  context('when the current page is "/"', () => {
    it('renders the home page', () => {
      renderApp('/');

      screen.getByText(/Welcome/);
    });
  });

  context('when the current page is "/products"', () => {
    it('renders the product list page', () => {
      renderApp('/products');

      screen.getByText(/Product list/);
    });
  });

  context('when the current page is "/about"', () => {
    it('renders the about page', () => {
      renderApp('/about');

      screen.getByText(/About Demo App/);
    });
  });

  context('when the current page is "/login"', () => {
    it('renders the login page', () => {
      renderApp('/login');

      screen.getByText(/Username/);
    });
  });
});
