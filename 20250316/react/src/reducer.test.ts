import { describe, expect, it } from 'vitest';

import reducer from './reducer';

import { Ticket } from './types';

describe('reducer', () => {
  describe('addTicket', () => {
    it('adds a ticket', () => {
      const prevTickets: Ticket[] = [];

      const newTickets = reducer(prevTickets, {
        type: 'addTicket',
        title: 'TITLE',
        description: 'DESCRIPTION',
      });

      expect(newTickets).toEqual([
        {
          id: 1,
          title: 'TITLE',
          description: 'DESCRIPTION',
          status: 'open',
          comments: [],
        },
      ]);
    });
  });

  describe('toggleTicketStatus', () => {
    it('toggles ticket status', () => {
      const prevTickets: Ticket[] = [
        {
          id: 1,
          title: 'TITLE',
          description: 'DESCRIPTION',
          status: 'open',
          comments: [],
        },
      ];

      const newTickets = reducer(prevTickets, {
        type: 'toggleTicketStatus',
        id: 1,
      });

      expect(newTickets).toEqual([
        {
          id: 1,
          title: 'TITLE',
          description: 'DESCRIPTION',
          status: 'closed',
          comments: [],
        },
      ]);
    });
  });

  describe('addComment', () => {
    it('adds a comment', () => {
      const prevTickets: Ticket[] = [
        {
          id: 1,
          title: 'TITLE',
          description: 'DESCRIPTION',
          status: 'open',
          comments: [],
        },
      ];

      const newTickets = reducer(prevTickets, {
        type: 'addComment',
        ticketId: 1,
        content: 'COMMENT',
      });

      expect(newTickets).toEqual([
        {
          id: 1,
          title: 'TITLE',
          description: 'DESCRIPTION',
          status: 'open',
          comments: [
            {
              id: 1,
              content: 'COMMENT',
            },
          ],
        },
      ]);
    });
  });
});
