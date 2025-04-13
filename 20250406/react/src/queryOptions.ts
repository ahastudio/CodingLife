import { queryOptions } from '@tanstack/react-query';

import { fetchTicket, fetchTickets } from './api';

import { TICKETS_QUERY_KEY } from './contants';

export function ticketsQueryOptions() {
  return queryOptions({
    queryKey: [TICKETS_QUERY_KEY],
    queryFn: fetchTickets,
  });
}

export function ticketQueryOptions(ticketId: string) {
  return queryOptions({
    queryKey: [TICKETS_QUERY_KEY, ticketId],
    queryFn: () => fetchTicket({ ticketId }),
  });
}
