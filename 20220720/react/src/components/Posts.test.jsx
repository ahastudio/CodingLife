import { render, screen, waitFor } from '@testing-library/react';

import Posts from './Posts';

jest.mock('../hooks/useFetchPosts', () => () => ({
  posts: [
    {
      id: 1, title: 'Test', body: '...', author: 'Ashal',
    },
  ],
  fetchPosts: jest.fn(),
}));

test('Posts', async () => {
  render(<Posts />);

  await waitFor(() => {
    screen.getByText(/Test/);
  });
});
