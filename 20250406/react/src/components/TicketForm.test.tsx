import nock from 'nock';

import { beforeEach, describe, expect, it } from 'vitest';

import { fireEvent, render, screen, waitFor } from '@testing-library/react';

import TicketForm from './TicketForm';

import { API_BASE_URL } from '../api';

import { Ticket } from '../types';

const context = describe;

describe('TicketForm', () => {
  // biome-ignore lint/suspicious/noExplicitAny:
  let requestBody: any = null;

  const ticket: Ticket = {
    id: 'ticket-1',
    title: 'New Ticket',
    description: 'New Ticket Description',
    status: 'open',
    comments: [],
  };

  beforeEach(() => {
    requestBody = null;

    nock(API_BASE_URL)
      .post('/tickets')
      .reply(201, (_uri, body) => {
        requestBody = body;
        return ticket;
      });
  });

  function renderTicketForm() {
    render(<TicketForm />);
  }

  context('when user fills and submits a new ticket', () => {
    it('calls API', async () => {
      renderTicketForm();

      fireEvent.change(screen.getByRole('textbox', { name: /Title/ }), {
        target: { value: 'New Ticket' },
      });
      fireEvent.change(screen.getByRole('textbox', { name: /Description/ }), {
        target: { value: 'New Ticket Description' },
      });
      fireEvent.click(screen.getByRole('button', { name: /Add Ticket/ }));

      await waitFor(() => {
        expect(requestBody).toBeTruthy();
        expect(requestBody.title).toBe('New Ticket');
        expect(requestBody.description).toBe('New Ticket Description');
      });
    });
  });
});
