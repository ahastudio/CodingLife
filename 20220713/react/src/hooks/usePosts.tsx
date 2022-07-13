import { useEffect, useState } from 'react';

import { Post, fetchPosts } from '../services/api';

export default function usePosts() {
  const [posts, setPosts] = useState<Post[]>([]);

  const loadPosts = async () => {
    setPosts(await fetchPosts());
  };

  useEffect(() => {
    loadPosts();
  }, []);

  return posts;
}
