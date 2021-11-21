import { useQuery } from 'urql';

import AddTaskButton from './AddTaskButton';

const { log } = console;

interface Task {
  id: number;
  title: String;
  status: String;
}

const TASKS_LIST_QUERY = `
  query ($search: String) {
    tasks(search: $search) {
      id
      title
      status
    }
  }
`;

export default function Tasks() {
  const [result, reexecuteQuery] = useQuery({
    query: TASKS_LIST_QUERY,
    requestPolicy: 'cache-and-network',
  });

  log('[query]', result);

  const { fetching, error, data } = result;

  const handleReload = () => {
    log('Reload!');
    reexecuteQuery();
  };

  if (fetching) {
    return (
      <p>Loading...</p>
    );
  }

  if (error) {
    return (
      <p>
        Error:
        {' '}
        {JSON.stringify(error)}
      </p>
    );
  }

  const { tasks } = data;

  return (
    <div>
      <ul>
        {tasks.map((task: Task) => (
          <li key={task.id}>
            {task.title}
            {' '}
            |
            {' '}
            {task.status}
          </li>
        ))}
      </ul>
      <hr />
      <div>
        <AddTaskButton reload={reexecuteQuery} />
        {' '}
        <button type="button" onClick={handleReload}>
          Reload!
        </button>
      </div>
    </div>
  );
}
