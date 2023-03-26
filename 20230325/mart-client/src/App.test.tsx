import { render, screen, waitFor } from '@testing-library/react';

import App from './App';

test('App', async () => {
  render(<App />);

  await waitFor(() => {
    screen.getByText(/Product List/);
  });
});
