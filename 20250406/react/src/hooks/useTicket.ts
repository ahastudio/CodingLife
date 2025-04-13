import { useSuspenseQuery } from '@tanstack/react-query';

import { ticketQueryOptions } from '../queryOptions';

export default function useTicket({
  ticketId,
}: {
  ticketId: string;
}) {
  const { data, error, isFetching } = useSuspenseQuery(
    ticketQueryOptions(ticketId),
  );

  if (error && !isFetching) {
    throw error;
  }

  return data;
}
