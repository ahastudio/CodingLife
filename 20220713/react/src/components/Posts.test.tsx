import { render, screen, waitFor } from '@testing-library/react';

import Posts from './Posts';

describe('Posts', () => {
  it('renders posts', async () => {
    render(<Posts />);

    await waitFor(() => {
      screen.getByText(/Bye/);
    });
  });
});
