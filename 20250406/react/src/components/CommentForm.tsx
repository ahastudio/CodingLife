import { revalidatePath } from 'next/cache';

import TextField from './TextField';
import SubmitButton from './SubmitButton';

import { createComment } from '../api';
import { invalidateCache } from '../cache';

import { TICKETS_QUERY_KEY } from '../contants';

export default function CommentForm({
  ticketId,
}: {
  ticketId: string;
}) {
  const handleSubmit = async (formData: FormData) => {
    'use server';

    const content = formData.get('content') as string;

    await createComment({ ticketId, content });

    await invalidateCache(TICKETS_QUERY_KEY);

    revalidatePath(`/tickets/${ticketId}`);
  };

  return (
    <form action={handleSubmit}>
      <TextField label="Comment" name="content" placeholder="Comment" />
      <SubmitButton label="Add Comment" />
    </form>
  );
}
