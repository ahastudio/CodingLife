import Ticket from '../models/Ticket';

export default function useFilterTickets() {
  const tickets: Ticket[] = [
    new Ticket({ id: '1', title: 'Ticket #1', status: 'todo' }),
    new Ticket({ id: '2', title: 'Ticket #2', status: 'doing' }),
    new Ticket({ id: '3', title: 'Ticket #3', status: 'done' }),
    new Ticket({ id: '4', title: 'Ticket #4', status: 'done' }),
    new Ticket({ id: '5', title: 'Ticket #5', status: 'todo' }),
    new Ticket({ id: '6', title: 'Ticket #6', status: 'todo' }),
    new Ticket({ id: '7', title: 'Ticket #7', status: 'done' }),
    new Ticket({ id: '8', title: 'Ticket #8', status: 'todo' }),
    new Ticket({ id: '9', title: 'Ticket #9', status: 'doing' }),
    new Ticket({ id: '10', title: 'Ticket #10', status: 'todo' }),
  ];

  return ({ status }: {
    status: string;
  }) => (
    tickets.filter((i) => i.status === status)
  );
}
