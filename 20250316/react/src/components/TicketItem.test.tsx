import { beforeEach, describe, expect, it, vi } from 'vitest';

import { fireEvent, render, screen } from "@testing-library/react";

import TicketItem from './TicketItem';

import { Ticket } from '../types';

const context = describe;

describe('TicketItem', () => {
  const ticket: Ticket = {
    id: 1,
    title: 'TITLE',
    description: 'DESCRIPTION',
    status: 'open',
    comments: [
      { id: 1, content: 'COMMENT' },
    ],
  };

  const dispatch = vi.fn();

  beforeEach(() => {
    vi.resetAllMocks();
  });

  function renderTicketItem() {
    render(
      <TicketItem ticket={ticket} dispatch={dispatch} />
    );
  }

  it('renders title and description', () => {
    renderTicketItem();

    screen.getByText('TITLE');
    screen.getByText('DESCRIPTION');
  });

  it('renders comments', () => {
    renderTicketItem();

    screen.getByText('COMMENT');
  });

  context('when user clicks toggle button', () => {
    it('calls dispatch function', () => {
      renderTicketItem();

      fireEvent.click(screen.getByRole('button', { name: /Open/ }));

      expect(dispatch).toBeCalled();

      const firstArguments = dispatch.mock.calls[0];

      const action = firstArguments[0];

      expect(action.type).toBe('toggleTicketStatus');
      expect(action.id).toBe(ticket.id);

      expect(action).toEqual({
        type: 'toggleTicketStatus',
        id: ticket.id,
      });
    });
  });

  context('when user submits comment', () => {
    it('calls dispatch function', () => {
      renderTicketItem();

      fireEvent.change(screen.getByRole('textbox', { name: /Comment/ }), {
        target: { value: 'New Comment' },
      });
      fireEvent.click(screen.getByRole('button', { name: /Add Comment/ }));

      expect(dispatch).toBeCalled();

      const firstArguments = dispatch.mock.calls[0];

      const action = firstArguments[0];

      expect(action.type).toBe('addComment');
      expect(action.ticketId).toBe(ticket.id);
      expect(action.content).toBe('New Comment');
    });
  });
});
