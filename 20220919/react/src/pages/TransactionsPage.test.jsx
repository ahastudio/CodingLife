import { render, screen, waitFor } from '@testing-library/react';

import TransactionsPage from './TransactionsPage';

test('TransactionsPage', async () => {
  render(<TransactionsPage />);

  await waitFor(() => {
    screen.getByText(/3,000원/);
  });
});
