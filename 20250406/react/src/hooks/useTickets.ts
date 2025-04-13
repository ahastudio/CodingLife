import { useSuspenseQuery } from '@tanstack/react-query';

import { ticketsQueryOptions } from '../queryOptions';

export default function useTickets() {
  const { data, error, isFetching } = useSuspenseQuery(ticketsQueryOptions());

  if (error && !isFetching) {
    throw error;
  }

  const tickets = data?.tickets || [];

  return { tickets };
}
