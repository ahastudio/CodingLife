import useFetch from './useFetch';

import { User } from '../types';

export default function useFetchUsers() {
  const { data, error, loading } = useFetch<{
    users: User[];
  }>('/users');

  return {
    users: data?.users ?? [],
    error,
    loading,
  };
}
