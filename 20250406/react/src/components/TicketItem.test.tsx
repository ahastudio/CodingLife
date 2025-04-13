import nock from 'nock';

import { beforeEach, describe, expect, it, vi } from 'vitest';

import { fireEvent, render, screen, waitFor } from '@testing-library/react';

import TicketItem from './TicketItem';

import { API_BASE_URL } from '../api';

import { Ticket } from '../types';

vi.mock('next/cache');

describe('TicketItem', () => {
  let requestTicketId = '';

  // biome-ignore lint/suspicious/noExplicitAny:
  let requestBody: any = null;

  const ticket: Ticket = {
    id: 'ticket-1',
    title: 'TITLE',
    description: 'DESCRIPTION',
    status: 'open',
    comments: [{ id: 'comment-1', content: 'COMMENT' }],
  };

  beforeEach(() => {
    requestTicketId = '';
    requestBody = null;

    nock(API_BASE_URL)
      .patch(`/tickets/${ticket.id}`)
      .reply(200, (uri, body) => {
        const parts = uri.split('/');
        requestTicketId = parts[parts.length - 1];
        requestBody = body;
        return {
          ...ticket,
          status: body.status,
        };
      });
  });

  function renderTicketItem() {
    render(<TicketItem ticket={ticket} />);
  }

  it('renders title and status', () => {
    renderTicketItem();

    screen.getByText('TITLE');
    screen.getByText(/Open/);
  });
});
