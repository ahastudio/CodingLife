import Tasks from './Tasks';

import useTodoService from '../hooks/useTodoService';

import Task from '../models/Task';

const { log } = console;

export default function TaskList() {
  log('TaskList');

  const todoService = useTodoService();

  const handleToggle = (task: Task) => {
    todoService.toggleTask(task.id);
  };

  return (
    <Tasks toggle={handleToggle} />
  );
}
