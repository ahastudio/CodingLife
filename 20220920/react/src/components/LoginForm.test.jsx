import {
  render, screen, fireEvent, waitFor,
} from '@testing-library/react';

import { ThemeProvider } from 'styled-components';

import LoginForm from './LoginForm';

import defaultTheme from '../styles/defaultTheme';

const navigate = jest.fn();

jest.mock('react-router-dom', () => ({
  useNavigate() {
    return navigate;
  },
}));

test('LoginForm', async () => {
  render((
    <ThemeProvider theme={defaultTheme}>
      <LoginForm />
    </ThemeProvider>
  ));

  screen.getByRole('heading', { name: '로그인' });

  fireEvent.change(screen.getByLabelText('계좌 번호'), {
    target: { value: '1234' },
  });
  fireEvent.change(screen.getByLabelText('패스워드'), {
    target: { value: 'password' },
  });
  fireEvent.click(screen.getByRole('button', { name: '로그인' }));

  await waitFor(() => {
    expect(navigate).toBeCalledWith('/');
  });
});
