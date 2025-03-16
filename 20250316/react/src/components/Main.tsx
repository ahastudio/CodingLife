import TicketList from './TicketList';
import TicketForm from './TicketForm';

import useTickets from '../hooks/useTickets';

export default function Main() {
  const { tickets, dispatch } = useTickets();

  return (
    <main>
      <TicketList tickets={tickets} dispatch={dispatch} />
      <TicketForm dispatch={dispatch} />
    </main>
  );
}
