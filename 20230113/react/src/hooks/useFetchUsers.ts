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
  type UsersRespone = {
    data: User[];
  }

  const url = 'https://reqres.in/api/users';
  const response = useFetch<UsersRespone>(url);
  if (!response.data) {
    return [];
  }
  const { data: users } = response.data;
  return users;
}
