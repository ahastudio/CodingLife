import { render, screen, waitFor } from '@testing-library/react';

import Posts from './Posts';

jest.mock('axios', () => {
  const mockAxios = {
    create() {
      return mockAxios;
    },
    async get() {
      return {
        data: [
          {
            id: 1, title: 'Test', body: '...', author: 'Ashal',
          },
        ],
      };
    },
  };
  return mockAxios;
});

test('Posts', async () => {
  render(<Posts />);

  await waitFor(() => {
    screen.getByText(/Test/);
  });
});
