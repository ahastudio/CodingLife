import { useMutation, useQueryClient } from '@tanstack/react-query';

import { createTicket, TicketListDto } from '../api';

import { TICKETS_QUERY_KEY } from '../contants';

export default function useCreateTicket() {
  const queryClient = useQueryClient();

  const { mutate } = useMutation({
    mutationFn: createTicket,
    onMutate: ({ title, description }) => {
      queryClient.cancelQueries({ queryKey: [TICKETS_QUERY_KEY] });
      const previousTickets = queryClient.getQueryData([TICKETS_QUERY_KEY]);
      queryClient.setQueryData([TICKETS_QUERY_KEY], (old: TicketListDto) => ({
        ...old,
        tickets: [
          ...(old?.tickets || []),
          {
            id: '',
            title,
            description,
            status: 'open',
            comments: [],
          },
        ],
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
