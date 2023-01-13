import { useRef } from 'react';

export default async function useCreateUserImmediately({ name, job }: {
  name: string;
  job: string;
}) {
  const cache = useRef<Record<string, unknown>>({});

  if (cache.current[name]) {
    return;
  }

  const url = 'https://reqres.in/api/users';
  const response = await fetch(url, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ name, job }),
  });

  const data = await response.json();

  cache.current[name] = data;

  setTimeout(() => {
    delete cache.current[name];
  }, 1_000);
}
