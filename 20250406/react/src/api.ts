import axios from 'axios';

import { Ticket } from './types';

export interface TicketListDto {
  tickets: Ticket[];
}

export const API_BASE_URL = 'https://tickets-api.codedemo.co';

const instance = axios.create({
  baseURL: API_BASE_URL,
  timeout: 10_000,
});

export async function fetchTickets(): Promise<TicketListDto> {
  const { data } = await instance.get('/tickets');
  console.log('🌏 fetchTickets', data);
  return data;
}

export async function fetchTicket({
  ticketId,
}: {
  ticketId: string;
}): Promise<Ticket> {
  const { data } = await instance.get(`/tickets/${ticketId}`);
  return data;
}

export async function createTicket({
  title,
  description,
}: {
  title: string;
  description: string;
}) {
  await instance.post('/tickets', { title, description });
}

export async function updateTicketStatus({
  id,
  status,
}: {
  id: string;
  status: 'open' | 'closed';
}) {
  await instance.patch(`/tickets/${id}`, { status });
}

export async function createComment({
  ticketId,
  content,
}: {
  ticketId: string;
  content: string;
}) {
  await instance.post(`/tickets/${ticketId}/comments`, { content });
}
