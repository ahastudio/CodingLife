import TicketItem from './TicketItem';

import { fetchTickets } from '../api';

import { Ticket } from '../types';
import { readCache, writeCache } from '../cache';

import { TICKETS_QUERY_KEY } from '../contants';

export function TicketList({
  tickets,
}: {
  tickets: Ticket[];
}) {
  return (
    <ul className="ticket-list">
      {tickets.map((ticket) => (
        <TicketItem key={ticket.id} ticket={ticket} />
      ))}
    </ul>
  );
}

async function getTickets(): Promise<Ticket[]> {
  const tickets = await readCache<Ticket[]>(TICKETS_QUERY_KEY);
  if (tickets) {
    return tickets;
  }

  const data = await fetchTickets();
  await writeCache(TICKETS_QUERY_KEY, data.tickets);
  return data.tickets;
}

export default async () => {
  const tickets = await getTickets();

  return <TicketList tickets={tickets} />;
};
