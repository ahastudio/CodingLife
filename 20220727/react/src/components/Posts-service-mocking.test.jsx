import { render, screen, waitFor } from '@testing-library/react';

import Posts from './Posts';

import { postStore } from '../stores/PostStore';

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
  postStore.fetchPosts();

  render(<Posts />);

  await waitFor(() => {
    screen.getByText(/Test/);
  });
});
