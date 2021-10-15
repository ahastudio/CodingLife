import { render } from '@testing-library/react';

import Posts from './Posts';

import { usePosts } from '../hooks';

jest.mock('../hooks');

describe('Posts', () => {
  const posts = [
    { id: 1, title: 'Title', body: 'Body' },
  ];

  beforeEach(() => {
    const hook = usePosts as jest.MockedFunction<typeof usePosts>;
    hook.mockReturnValue({
      posts,
      loadPosts: jest.fn(),
      addPost: jest.fn(),
    });
  });

  it('renders a list of post', () => {
    const { container } = render(<Posts />);

    expect(container).toHaveTextContent('Title');
    expect(container).toHaveTextContent('Body');
  });
});
