import useFetch from './useFetch';

import { apiService } from '../services/ApiService';

import { Category } from '../types';

export default function useFetchCategories() {
  type Data = {
    categories: Category[];
  };

  const {
    data, error, loading, mutate,
  } = useFetch<Data>('/categories');

  return {
    categories: data?.categories ?? [],
    error,
    loading,
    async createCategory({ name }: {
      name: string;
    }) {
      await apiService.createCategory({ name });
      mutate();
    },
  };
}
