import { render, fireEvent } from '@testing-library/react';

import PostForm from './PostForm';

import { usePosts } from '../hooks';

jest.mock('../hooks');

describe('PostForm', () => {
  const addPost = jest.fn();

  beforeEach(() => {
    jest.clearAllMocks();

    const hook = usePosts as jest.MockedFunction<typeof usePosts>;
    hook.mockReturnValue({
      posts: [],
      loadPosts: jest.fn(),
      addPost,
    });
  });

  it('listens to the “add” button click event', () => {
    const { getByText } = render(<PostForm />);

    fireEvent.click(getByText('Add post!'));

    expect(addPost).toBeCalled();
  });
});
