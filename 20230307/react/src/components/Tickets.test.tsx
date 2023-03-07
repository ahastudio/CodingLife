import { render, screen } from '@testing-library/react';

import Tickets from './Tickets';

import Ticket from '../models/Ticket';

const context = describe;

describe('Tickets', () => {
  context('with tickets', () => {
    it('renders tickets', () => {
      const tickets = [
        new Ticket({ id: '1', title: 'Item', status: 'todo' }),
      ];

      render(<Tickets tickets={tickets} />);

      screen.getByText(/Item/);
    });
  });

  context('without tickets', () => {
    it('renders nothing', () => {
      const tickets: Ticket[] = [];

      const { container } = render(<Tickets tickets={tickets} />);

      expect(container.textContent).toBe('');
    });
  });
});
