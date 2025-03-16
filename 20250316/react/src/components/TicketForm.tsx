import { FormEvent } from 'react';

import TextField from './TextField';
import TextArea from './TextArea';
import SubmitButton from './SubmitButton';

import { Dispatch } from '../types';

export default function TicketForm({ dispatch }: {
  dispatch: Dispatch;
}) {
  const handleSubmit = (event: FormEvent) => {
    event.preventDefault();

    const form = event.target as HTMLFormElement;
    const formData = new FormData(form);
    const title = formData.get('title') as string;
    const description = formData.get('description') as string;

    dispatch({ type: 'addTicket', title, description });

    form.reset();
  };

  return (
    <form id="add-ticket-form" onSubmit={handleSubmit}>
      <TextField label="Title" name="title" placeholder="Title" />
      <TextArea label="Description" name="description" placeholder="Description" />
      <SubmitButton label="Add Ticket" />
    </form>
  );
}
