import TicketStatus from './TicketStatus';

import CommentList from '../../../components/CommentList';
import CommentForm from '../../../components/CommentForm';

import { fetchTicket } from '../../../api';

export default async function TicketDetail({
  ticketId,
}: {
  ticketId: string;
}) {
  const ticket = await fetchTicket({ ticketId });

  return (
    <div className="ticket-detail">
      <div className="title">{ticket.title}</div>
      <div className="description">{ticket.description}</div>
      <TicketStatus ticket={ticket} />
      <CommentList comments={ticket.comments} />
      <CommentForm ticketId={ticket.id} />
    </div>
  );
}
