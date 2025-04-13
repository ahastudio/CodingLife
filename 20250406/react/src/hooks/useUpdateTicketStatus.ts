import { useMutation, useQueryClient } from '@tanstack/react-query';

import { TicketListDto, updateTicketStatus } from '../api';

import { TICKETS_QUERY_KEY } from '../contants';

export default function useUpdateTicketStatus() {
  const queryClient = useQueryClient();

  const { mutate } = useMutation({
    mutationFn: updateTicketStatus,
    onMutate: ({ id, status }) => {
      queryClient.cancelQueries({ queryKey: [TICKETS_QUERY_KEY] });
      const previousTickets = queryClient.getQueryData([TICKETS_QUERY_KEY]);
      queryClient.setQueryData([TICKETS_QUERY_KEY], (old: TicketListDto) => ({
        ...old,
        tickets: (old?.tickets || []).map((ticket) =>
          ticket.id === id ? { ...ticket, status } : ticket,
        ),
      }));
      return { previousTickets };
    },
    onSettled: () => {
      queryClient.invalidateQueries({ queryKey: [TICKETS_QUERY_KEY] });
    },
    onError: (_error, _variables, context) => {
      queryClient.setQueryData([TICKETS_QUERY_KEY], context?.previousTickets);
    },
  });

  return mutate;
}
