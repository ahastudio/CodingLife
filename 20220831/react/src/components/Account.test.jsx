import { render, screen } from '@testing-library/react';

import Account from './Account';

import { bankStore } from '../stores/BankStore';

test('Account', async () => {
  await bankStore.fetchAccount();

  render(<Account />);

  screen.getByText(/이름: Tester/);
  screen.getByText(/계좌번호: 1234/);
  screen.getByText(/잔액: 100,000원/);
});
