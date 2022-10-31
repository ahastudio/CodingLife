import { useEffect } from 'react';

import Posts from '../components/Posts';

import useBoardStore from '../hooks/useBoardStore';

export default function Home() {
  const [, store] = useBoardStore();

  useEffect(() => {
    store.fetchPosts();
  }, []);

  return (
    <div>
      <h1>Posts</h1>
      <Posts />
    </div>
  );
}
