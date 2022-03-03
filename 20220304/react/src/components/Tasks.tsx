import { useRecoilValue } from 'recoil';

import Task from '../models/Task';

import { tasksState } from '../states/todo';

const { log } = console;

type TasksProps = {
  toggle: (task: Task) => void;
}

export default function Tasks({ toggle }: TasksProps) {
  log('Tasks');

  const tasks = useRecoilValue(tasksState);

  if (!tasks.length) {
    return (
      <p>No task...</p>
    );
  }

  return (
    <ul>
      {tasks.map((task) => (
        <li key={task.id}>
          <span
            style={{
              textDecoration: task.completed ? 'line-through' : '',
            }}
          >
            {task.title}
          </span>
          {' '}
          <button type="button" onClick={() => toggle(task)}>
            Toggle
          </button>
        </li>
      ))}
    </ul>
  );
}
