import { render, screen, waitFor } from '@testing-library/react';

import Posts from './Posts';

jest.mock('../services/PostService', () => ({
  postService: {
    async fetchPosts() {
      return [
        {
          id: 1, title: 'Test', body: '...', author: 'Ashal',
        },
      ];
    },
  },
}));

test('Posts', async () => {
  render(<Posts />);

  await waitFor(() => {
    screen.getByText(/Test/);
  });
});
