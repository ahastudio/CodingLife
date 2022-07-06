import { useState } from 'react';

import ToDoList from './ToDoList';
import ToDoInput from './ToDoInput';

export default function ToDoApp() {
  const [tasks, setTasks] = useState([]);

  const addTask = (title) => {
    const lastestTask = tasks[tasks.length - 1];
    const id = lastestTask ? (lastestTask.id + 1) : 1;
    // const id = (lastestTask?.id || 0) + 1;
    setTasks([
      ...tasks,
      { id, title },
    ]);
  };

  return (
    <div>
      <ToDoList tasks={tasks} />
      <ToDoInput addTask={addTask} />
    </div>
  );
}
