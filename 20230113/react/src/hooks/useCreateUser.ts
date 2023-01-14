import { useRef } from 'react';

export default function useCreateUser() {
  const cache = useRef<Record<string, unknown>>({});

  return {
    async createUser({ name, job }: {
      name: string;
      job: string;
    }) {
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
      console.log('Create User:', data);

      cache.current[name] = data;

      setTimeout(() => {
        delete cache.current[name];
      }, 1_000);
    },
  };
}
