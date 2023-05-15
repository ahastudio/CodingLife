import {
  render, screen, fireEvent, waitFor,
} from '@testing-library/react';

import { createMemoryRouter, RouterProvider } from 'react-router-dom';

import { ThemeProvider } from 'styled-components';

import defaultTheme from './styles/defaultTheme';

import routes from './routes';

import fixtures from '../fixtures';

let accessToken = '';

jest.mock('./hooks/useAccessToken', () => () => ({
  accessToken,
  setAccessToken(token: string) {
    accessToken = token;
  },
}));

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

  context('without logged in', () => {
    beforeEach(() => {
      accessToken = '';
    });

    context('when the current path is “/”', () => {
      it('redirects to the login page', async () => {
        renderRouter('/');

        await waitFor(() => {
          screen.getByRole('heading', { name: /로그인/ });
        });
      });
    });

    context('when the current path is “/login”', () => {
      it('renders the login page', async () => {
        renderRouter('/login');

        screen.getByRole('heading', { name: '로그인' });

        fireEvent.change(screen.getByLabelText('E-mail'), {
          target: { value: 'newbie@example.com' },
        });

        fireEvent.change(screen.getByLabelText('Password'), {
          target: { value: 'password' },
        });

        fireEvent.click(screen.getByRole('button', { name: '로그인' }));

        await waitFor(() => {
          screen.getByText(/Hello/);
        });
      });
    });
  });

  context('with logged in', () => {
    beforeEach(() => {
      accessToken = 'ACCESS_TOKEN';
    });

    context('when the current path is “/”', () => {
      it('renders the home page', async () => {
        renderRouter('/');

        await waitFor(() => {
          screen.getByText(/Hello/);
        });
      });
    });

    context('when the current path is “/users', () => {
      const [user] = fixtures.users;

      it('renders the user list page', async () => {
        renderRouter('/users');

        await waitFor(() => {
          screen.getByText(user.name);
        });
      });
    });

    context('when the current path is “/categories', () => {
      const [category] = fixtures.categories;

      it('renders the category list page', async () => {
        renderRouter('/categories');

        await waitFor(() => {
          screen.getByText(category.name);
        });
      });
    });

    context('when the current path is “/categories/new', () => {
      it('renders the new category page', async () => {
        renderRouter('/categories/new');

        await waitFor(() => {
          screen.getByText(/등록/);
        });
      });
    });

    context('when the current path is “/categories/{id}/edit', () => {
      const [category] = fixtures.categories;

      it('renders the edit category page', async () => {
        renderRouter(`/categories/${category.id}/edit`);

        await waitFor(() => {
          screen.getByDisplayValue(category.name);
        });
      });
    });

    context('when the current path is “/products', () => {
      const [product] = fixtures.products;

      it('renders the product list page', async () => {
        renderRouter('/products');

        await waitFor(() => {
          screen.getByText(product.name);
        });
      });
    });

    context('when the current path is “/products/{id}', () => {
      const [product] = fixtures.products;

      it('renders the product detail page', async () => {
        renderRouter(`/products/${product.id}`);

        await waitFor(() => {
          screen.getByText(product.name);
        });
      });
    });

    context('when the current path is “/products/new', () => {
      it('renders the new product page', async () => {
        renderRouter('/products/new');

        await waitFor(() => {
          screen.getByText(/등록/);
        });
      });
    });

    context('when the current path is “/products/{id}/edit', () => {
      const [product] = fixtures.products;

      it('renders the edit product page', async () => {
        renderRouter(`/products/${product.id}/edit`);

        await waitFor(() => {
          screen.getByDisplayValue(product.name);
        });
      });
    });
  });
});
