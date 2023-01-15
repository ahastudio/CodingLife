import { useRef } from 'react';

import { Post } from '../types';

export default async function useCreateUserImmediately({ title, author }: {
  title: string;
  author: string;
}) {
  const cache = useRef<Record<string, Post>>({});

  if (cache.current[title]) {
    return;
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
}
