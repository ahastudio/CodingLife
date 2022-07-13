import { fetchPosts } from './api';

test('fetchPosts', async () => {
  const posts = await fetchPosts();

  expect(posts).toHaveLength(2);
});
