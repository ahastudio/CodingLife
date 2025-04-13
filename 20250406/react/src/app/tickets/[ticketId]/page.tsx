import { Suspense } from 'react';

import TicketDetail from './TicketDetail';

interface TicketPageProps {
  params: Promise<{
    ticketId: string;
  }>;
}

export async function generateMetadata({ params }: TicketPageProps) {
  const { ticketId } = await params;

  return {
    title: `Ticket #${ticketId}`,
  };
}

export default async function TicketsPage({ params }: TicketPageProps) {
  const { ticketId } = await params;

  return (
    <Suspense fallback={<div>Loading...</div>}>
      <TicketDetail ticketId={ticketId} />
    </Suspense>
  );
}
