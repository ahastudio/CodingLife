import { useState } from 'react';

import { useEffectOnce, useFetch } from 'usehooks-ts';

interface User {
  id: number;
  email: string;
  first_name: string;
  last_name: string;
  avatar: string;
}

function useFetchUsersOld() {
  const [users, setUsers] = useState<User[]>([]);

  useEffectOnce(() => {
    const fetchUsers = async () => {
      const url = 'https://reqres.in/api/users';
      const response = await fetch(url);
      const data = await response.json();
      return data;
    };

    (async () => {
      const { data } = await fetchUsers();
      setUsers(data);
    })();
  });

  return users;
}

export default function useFetchUsers() {
  const url = 'https://reqres.in/api/users';
  const { data } = useFetch<User[]>(url);
  return data;
}
