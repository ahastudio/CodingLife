import { describe, it } from 'vitest';

import { render, screen } from '@testing-library/react';

import { TicketList } from './TicketList';

import { Ticket } from '../types';

describe('TicketList', () => {
  function renderTicketList() {
    const tickets: Ticket[] = [
      {
        id: 'ticket-1',
        title: 'Ticket #1',
        description: 'Ticket Description',
        status: 'open',
        comments: [
          {
            id: 'comment-1',
            content: 'Comment Content',
          },
        ],
      },
    ];

    render(<TicketList tickets={tickets} />);
  }

  it('renders tickets', () => {
    renderTicketList();

    screen.getByText(/Ticket #1/);
  });
});
