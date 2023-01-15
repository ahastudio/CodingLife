import { useRef } from 'react';

import { Post } from '../types';

export default function useCreatePost() {
  const cache = useRef<Record<string, Post>>({});

  return {
    async createPost({ title, author }: {
      title: string;
      author: string;
    }) {
      if (cache.current[title]) {
        return null;
      }

      const url = 'http://localhost:3000/posts';
      const response = await fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ title, author }),
      });

      const data = await response.json();

      cache.current[title] = data;

      setTimeout(() => {
        delete cache.current[title];
      }, 1_000);

      return data;
    },
  };
}
