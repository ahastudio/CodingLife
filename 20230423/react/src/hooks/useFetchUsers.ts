import useFetch from './useFetch';

import { User } from '../types';

export default function useFetchUsers() {
  type Data = {
    users: User[];
  };

  const { data, error, loading } = useFetch<Data>('/users');

  return {
    users: data?.users ?? [],
    error,
    loading,
  };
}
