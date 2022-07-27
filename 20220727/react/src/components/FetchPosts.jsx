import { useEffect } from 'react';

import usePostStore from '../hooks/usePostStore';

export default function FetchPosts() {
  const postStore = usePostStore();

  useEffect(() => {
    postStore.fetchPosts();
  }, []);

  return null;
}
