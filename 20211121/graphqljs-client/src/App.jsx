import { h } from 'preact';
import { useState, useEffect } from 'preact/hooks';

import graphql from 'graphql.js';

const { log } = console;

const graph = graphql('http://localhost:3000/graphql');

const allTasks = graph.query(`{
  tasks { id, title, status }
}`);

const addTask = graph.mutate(`(@autodeclare) {
  addTask(title: $title) { id, title, status }
}`);

export default function App() {
  const [tasks, setTasks] = useState([]);

  const loadTasks = async () => {
    const data = await allTasks();
    log(data);
    setTasks(data.tasks);
  };

  const handleAddTask = async () => {
    const data = await addTask({ title: `Task-${new Date().getTime()}` });
    log(data);
    loadTasks();
  };

  useEffect(() => {
    loadTasks();
  }, []);

  return (
    <div>
      <p>Hello, world!</p>
      <ul>
        {tasks.map((task) => (
          <li key={task.id}>
            {task.title}
            {' '}
            (
            {task.status}
            )
          </li>
        ))}
      </ul>
      <hr />
      <button type="button" onClick={handleAddTask}>
        Add a new task!
      </button>
    </div>
  );
}
