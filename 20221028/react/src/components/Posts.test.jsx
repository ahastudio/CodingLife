import { render, screen } from '@testing-library/react';

import Posts from './Posts';

import fixtures from '../fixtures';

const state = {
  posts: [],
};

jest.mock('../hooks/useBoardStore', () => () => [state]);

const context = describe;

describe('Posts', () => {
  context('without posts', () => {
    beforeEach(() => {
      state.posts = [];
    });

    it('renders the “no posts” message', () => {
      render(<Posts />);

      screen.getByText(/게시물이 없습니다/);
    });
  });

  context('with posts', () => {
    beforeEach(() => {
      state.posts = fixtures.posts;
    });

    it('renders posts', () => {
      render(<Posts />);

      const [post] = fixtures.posts;

      screen.getByText(new RegExp(post.title));
    });
  });
});
