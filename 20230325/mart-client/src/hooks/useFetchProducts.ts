import { useFetch } from 'usehooks-ts';

import { Product } from '../types';

const baseUrl = process.env.API_BASE_URL || 'http://localhost:3000';

export default function useFetchProducts() {
  const url = `${baseUrl}/products`;

  const { data, error } = useFetch<Product[]>(url);

  return {
    products: data ?? [],
    loading: !data,
    error,
  };
}
