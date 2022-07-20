import { useEffect, useState } from 'react';

import { postService } from '../services/PostService';

export default function useFetchPosts() {
  const [posts, setPosts] = useState([]);

  const fetchPosts = async () => {
    // console.log('fetchPosts');
    const posts = await postService.fetchPosts();
    setPosts(posts);
  };

  useEffect(() => {
    fetchPosts();
  }, []);

  return { posts, fetchPosts };
}
