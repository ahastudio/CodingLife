import useTasks from '../hooks/useTasks';

import Task from '../models/Task';

export default function Tasks() {
  const { tasks, removeTask } = useTasks();

  if (!tasks.length) {
    return (
      <p>
        No tasks...
      </p>
    );
  }

  const handleClick = (task: Task) => {
    const { id } = task;
    return () => {
      removeTask(id);
    };
  };

  return (
    <ul>
      {tasks.map((task) => (
        <li key={task.id}>
          {task.title}
          {' '}
          <button type="button" onClick={handleClick(task)}>
            Done
          </button>
        </li>
      ))}
    </ul>
  );
}
