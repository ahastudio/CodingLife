import {
  render, screen, fireEvent, waitFor,
} from '@testing-library/react';

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

  context('when the current path is “/login”', () => {
    it('renders the login page', async () => {
      renderRouter('/login');

      screen.getByRole('heading', { name: '로그인' });

      await waitFor(() => {
        screen.getByText(/Category #1/);
      });

      fireEvent.change(screen.getByLabelText('E-mail'), {
        target: { value: 'newbie@example.com' },
      });

      fireEvent.change(screen.getByLabelText('Password'), {
        target: { value: 'password' },
      });

      fireEvent.click(screen.getByRole('button', { name: '로그인' }));

      await waitFor(() => {
        screen.getByText(/Orders/);
        screen.getByText(/Cart/);
        screen.getByText(/Logout/);
      });
    });
  });

  context('when the current path is “/signup', () => {
    it('renders the signup page', async () => {
      renderRouter('/signup');

      screen.getByRole('heading', { name: '회원 가입' });

      await waitFor(() => {
        screen.getByText(/Category #1/);
      });

      fireEvent.change(screen.getByLabelText('E-mail'), {
        target: { value: 'newbie@example.com' },
      });

      ['Password', 'Password Confirmation'].forEach((label) => {
        fireEvent.change(screen.getByLabelText(label), {
          target: { value: 'password' },
        });
      });

      fireEvent.click(screen.getByRole('button', { name: '회원 가입' }));

      await waitFor(() => {
        screen.getByText(/회원 가입이 완료되었습니다/);
      });
    });
  });

  context('when the current path is “/signup/complete', () => {
    it('renders the signup complete page', async () => {
      renderRouter('/signup/complete');

      screen.getByText(/회원 가입이 완료되었습니다/);

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

  context('when the current path is “/orders', () => {
    it('renders the order list page', async () => {
      renderRouter('/orders');

      await waitFor(() => {
        screen.getByText(/결제 금액/);
      });
    });
  });

  context('when the current path is “/orders/{id}', () => {
    context('with correct ID', () => {
      it('renders the product detail page', async () => {
        renderRouter('/orders/order-01');

        screen.getByText(/Loading/);

        await waitFor(() => {
          screen.getByText(/맨투맨/);
        });
      });
    });

    context('with incorrect ID', () => {
      it('renders “not found” message', async () => {
        renderRouter('/orders/xxx');

        await waitFor(() => {
          screen.getByText(/Error/);
        });
      });
    });
  });
});
