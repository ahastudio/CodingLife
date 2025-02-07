import { renderHook, act } from '@testing-library/react';

import { RecoilRoot } from 'recoil';

import { usePosts } from './hooks';

const posts = [
  { id: 1, title: 'title', body: 'body' },
  { id: 2, title: 'title', body: 'body' },
  { id: 3, title: 'title', body: 'body' },
];

jest.mock('axios', () => ({
  get: () => ({
    data: posts,
  }),
}));

describe('usePosts', () => {
  const wrapper = ({ children }: { children: any }) => (
    <RecoilRoot>{children}</RecoilRoot>
  );

  const render = () => renderHook(() => usePosts(), { wrapper });

  it('uses a list of post', () => {
    const { result } = render();

    expect(result.current.posts).toHaveLength(0);
  });

  describe('addPost', () => {
    it('appends a new post', () => {
      const { result } = render();

      act(() => {
        result.current.addPost();
      });

      expect(result.current.posts).toHaveLength(1);
    });
  });

  describe('loadPosts', () => {
    it('fetches a list of post', async () => {
      const { result } = render();

      await act(async () => {
        await result.current.loadPosts();
      });

      expect(result.current.posts).toBe(posts);
    });
  });
});
