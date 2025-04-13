import { revalidatePath } from 'next/cache';

import TextField from './TextField';
import TextArea from './TextArea';
import SubmitButton from './SubmitButton';

import { createTicket } from '../api';
import { invalidateCache } from '../cache';

import { TICKETS_QUERY_KEY } from '../contants';

export default function TicketForm() {
  const handleSubmit = async (formData: FormData) => {
    'use server';

    const title = formData.get('title') as string;
    const description = formData.get('description') as string;

    await createTicket({ title, description });
    await invalidateCache(TICKETS_QUERY_KEY);

    revalidatePath('/tickets');
  };

  return (
    <form action={handleSubmit} className="add-ticket-form">
      <TextField label="Title" name="title" placeholder="Title" />
      <TextArea
        label="Description"
        name="description"
        placeholder="Description"
      />
      <SubmitButton label="Add Ticket" />
    </form>
  );
}
