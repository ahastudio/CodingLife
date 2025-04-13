import { revalidatePath } from 'next/cache';

import { updateTicketStatus } from '../../../api';

import { Ticket } from '../../../types';
import { invalidateCache } from '../../../cache';

import { TICKETS_QUERY_KEY } from '../../../contants';

export default function TicketStatus({
  ticket,
}: {
  ticket: Ticket;
}) {
  const handleClick = async () => {
    'use server';

    await updateTicketStatus({
      id: ticket.id,
      status: ticket.status === 'open' ? 'closed' : 'open',
    });

    await invalidateCache(TICKETS_QUERY_KEY);

    revalidatePath(`/tickets/${ticket.id}`);
  };

  return (
    <button type="button" className="status" onClick={handleClick}>
      {ticket.status === 'open' ? 'Open' : 'Closed'}
    </button>
  );
}
