import { Ticket, TicketAction } from './types';

export const initialState: Ticket[] = [];

export default function reducer(tickets: Ticket[], action: TicketAction): Ticket[] {
  switch (action.type) {
    case 'addTicket': {
      const id = Math.max(...tickets.map((ticket) => ticket.id), 0) + 1;

      const ticket: Ticket = {
        id,
        title: action.title,
        description: action.description,
        status: 'open',
        comments: [],
      };

      return [...tickets, ticket];
    }

    case 'toggleTicketStatus': {
      return tickets.map((ticket) => (
        ticket.id === action.id
          ? { ...ticket, status: ticket.status === 'open' ? 'closed' : 'open' }
          : ticket
      ));
    }

    case 'addComment': {
      const commentIds = tickets.reduce((commentIds, ticket) => [
        ...commentIds,
        ...ticket.comments.map((comment) => comment.id)
      ], [] as number[]);

      const id = Math.max(...commentIds, 0) + 1;

      return tickets.map((ticket) => (
        ticket.id === action.ticketId
          ? {
            ...ticket,
            comments: [
              ...ticket.comments,
              {
                id,
                content: action.content
              },
            ],
          }
          : ticket
      ));
    }
  }
}
