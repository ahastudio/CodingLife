import { SyntheticEvent } from 'react';

import useTodoService from '../hooks/useTodoService';

const { log } = console;

export default function TaskForm() {
  log('TaskForm');

  const todoService = useTodoService();

  function handleSubmit(e: SyntheticEvent) {
    const target = e.target as typeof e.target & {
      title: { value: string };
    };
    const title = target.title.value;
    todoService.addTask(title);
    target.title.value = '';
    e.preventDefault();
  }

  return (
    <form aria-label="form" onSubmit={handleSubmit}>
      <input type="text" name="title" />
      <button type="submit">
        Add
      </button>
    </form>
  );
}
