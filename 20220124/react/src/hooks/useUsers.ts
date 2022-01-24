import { gql } from 'graphql-request';

import { useEffect, useState } from 'react';

import client from '../client';

const USERS_QUERY = gql`
  query UsersQuery {
    users {
      id
      role
      name
      avatar {
        id
        imageUrl
      }
    }
  }
`;

export default function useUsers() {
  const [users, setUsers] = useState<any[] | null>(null);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<any>(undefined);

  const loadUsers = async () => {
    try {
      const data = await client.request(USERS_QUERY);
      setUsers(data.users);
      setLoading(false);
    } catch (e) {
      setLoading(false);
      setError(e);
    }
  };

  useEffect(() => {
    loadUsers();
  }, []);

  return { users, loading, error };
}
