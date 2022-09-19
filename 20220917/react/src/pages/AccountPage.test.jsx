import { render, screen, waitFor } from '@testing-library/react';

import AccountPage from './AccountPage';

test('AccountPage', async () => {
  render(<AccountPage />);

  await waitFor(() => {
    screen.getByText(/이름: Tester/);
  });
});
