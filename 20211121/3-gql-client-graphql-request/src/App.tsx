import { h } from 'preact';
import { useState, useEffect } from 'preact/hooks';

import { GraphQLClient, gql } from 'graphql-request';

const client = new GraphQLClient('http://localhost:3000/graphql');

const { log } = console;

interface Task {
  id: number;
  title: String;
  status: String;
}

const TASKS_LIST_QUERY = gql`
  {
    tasks {
      id
      title
      status
    }
  }
`;

const ADD_TASK = gql`
  mutation ($title: String!) {
    addTask(title: $title) {
      id
      title
      status
    }
  }
`;

export default function App() {
  const [tasks, setTasks] = useState([]);

  const loadTasks = async () => {
    const data = await client.request(TASKS_LIST_QUERY);
    log(data);
    setTasks(data.tasks);
  };

  const handleAddTask = async () => {
    const data = await client.request(ADD_TASK, {
      title: `Task-${new Date().getTime()}`,
    });
    log(data);
    loadTasks();
  };

  const handleReload = () => {
    loadTasks();
  };

  useEffect(() => {
    loadTasks();
  }, []);

  return (
    <div>
      <p>Hello, world!</p>
      <ul>
        {tasks.map((task: Task) => (
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
      <div>
        <button type="button" onClick={handleAddTask}>
          Add a new task!
        </button>
        {' '}
        <button type="button" onClick={handleReload}>
          Reload!
        </button>
      </div>
    </div>
  );
}
