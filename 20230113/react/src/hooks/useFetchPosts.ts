import { useState } from 'react';

import { useEffectOnce, useFetch } from 'usehooks-ts';

import { Post } from '../types';

export default function useFetchPostsOld() {
  const [posts, setPosts] = useState<Post[]>([]);

  useEffectOnce(() => {
    const fetchPosts = async () => {
      const url = 'http://localhost:3000/posts';
      const response = await fetch(url);
      const data = await response.json();
      return data;
    };

    (async () => {
      const data = await fetchPosts();
      setPosts(data);
    })();
  });

  return posts;
}

function useFetchPosts() {
  const url = 'http://localhost:3000/posts';
  const { data } = useFetch<Post[]>(url);
  return data;
}
