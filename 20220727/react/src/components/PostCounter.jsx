import usePostStore from '../hooks/usePostStore';

export default function PostCounter() {
  const postStore = usePostStore();

  const { posts } = postStore;

  return (
    <p>
      Count:
      {' '}
      {posts.length}
    </p>
  );
}
