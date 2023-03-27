import { render, screen, waitFor } from '@testing-library/react';

import { createMemoryRouter, RouterProvider } from 'react-router-dom';

import { ThemeProvider } from 'styled-components';

import defaultTheme from './styles/defaultTheme';

import routes from './routes';

import fixtures from '../fixtures';

const context = describe;

describe('routes', () => {
  function renderRouter(path: string) {
    const router = createMemoryRouter(routes, { initialEntries: [path] });
    render((
      <ThemeProvider theme={defaultTheme}>
        <RouterProvider router={router} />
      </ThemeProvider>
    ));
  }

  context('when the current path is “/”', () => {
    it('renders the home page', async () => {
      renderRouter('/');

      await waitFor(() => {
        screen.getByText(/Category #1/);
      });
    });
  });

  context('when the current path is “/products”', () => {
    context('without category ID', () => {
      it('renders the product list page', async () => {
        renderRouter('/products');

        await waitFor(() => {
          screen.getByText(/Product #1/);
        });
      });
    });

    context('with category ID', () => {
      it('renders the product list page', async () => {
        renderRouter(`/products?categoryId=${fixtures.categories[0].id}`);

        await waitFor(() => {
          screen.getByText(/Product #1/);
        });
      });
    });
  });

  context('when the current path is “/products/{id}”', () => {
    context('with correct ID', () => {
      it('renders the product detail page', async () => {
        renderRouter('/products/product-01');

        screen.getByText(/Loading/);

        await waitFor(() => {
          screen.getByText(/Product #1/);
        });
      });
    });

    context('with incorrect ID', () => {
      it('renders “not found” message', async () => {
        renderRouter('/products/xxx');

        await waitFor(() => {
          screen.getByText(/Error/);
        });
      });
    });
  });

  context('when the current path is “/cart”', () => {
    it('renders the cart page', async () => {
      renderRouter('/cart');

      await waitFor(() => {
        screen.getByText(/합계/);
      });
    });
  });
});
