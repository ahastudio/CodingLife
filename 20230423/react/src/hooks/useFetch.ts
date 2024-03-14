import useSWR from 'swr';

import { apiService } from '../services/ApiService';

const API_BASE_URL = import.meta.env.API_BASE_URL || 'http://localhost:3000/admin';

export default function useFetch<Data>(path: string) {
  const url = `${API_BASE_URL}${path}`;

  const {
    data, error, isLoading, mutate,
  } = useSWR<Data>(url, apiService.fetcher());

  return {
    data,
    error,
    loading: isLoading,
    mutate,
  };
}
