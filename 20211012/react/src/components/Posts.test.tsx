import { render } from '@testing-library/react';

import Posts from './Posts';

jest.mock('../hooks', () => ({
  usePosts: () => ({
    posts: [
      { id: 1, title: 'Title', body: 'Body' },
    ],
    loadPosts: jest.fn(),
    addPost: jest.fn(),
  }),
}));

describe('Posts', () => {
  it('renders a list of post', () => {
    const { container } = render(<Posts />);

    expect(container).toHaveTextContent('Title');
    expect(container).toHaveTextContent('Body');
  });
});
