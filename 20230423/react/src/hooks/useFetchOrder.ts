import useFetch from './useFetch';

import { apiService } from '../services/ApiService';

import { OrderDetail } from '../types';

export default function useFetchOrder({ orderId }: {
  orderId: string;
}) {
  const {
    data, error, loading, mutate,
  } = useFetch<OrderDetail>(`/orders/${orderId}`);

  return {
    order: data,
    error,
    loading,
    async updateOrder({ status }: {
      status: string;
    }) {
      await apiService.updateOrder({ orderId, status });

      mutate();
    },
  };
}
