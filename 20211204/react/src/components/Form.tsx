import React, { useRef } from 'react';

import useTasks from '../hooks/useTasks';

export default function Form() {
  const id = useRef(`input-${new Date().getTime()}`);

  const { newTask, addTask, updateNewTaskTitle } = useTasks();

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    addTask();
  };

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = event.target;
    updateNewTaskTitle(value);
  };

  return (
    <form onSubmit={handleSubmit}>
      <label htmlFor={id.current}>
        Task
      </label>
      {' '}
      <input
        id={id.current}
        type="text"
        name="title"
        value={newTask.title}
        onChange={handleChange}
      />
      {' '}
      <button type="submit">
        Submit
      </button>
    </form>
  );
}
