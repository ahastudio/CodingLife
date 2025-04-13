import { Suspense } from 'react';

import TicketList from '../../components/TicketList';
import TicketForm from '../../components/TicketForm';

export const metadata = {
  title: 'Ticket List',
};

export default function TicketsPage() {
  return (
    <>
      <Suspense fallback={<div>Loading...</div>}>
        <TicketList />
      </Suspense>
      <TicketForm />
    </>
  );
}
