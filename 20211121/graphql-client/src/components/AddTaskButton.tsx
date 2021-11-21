import { useMutation } from 'urql';

const { log } = console;

const ADD_TASK = `
  mutation ($title: String!) {
    addTask(title: $title) {
      id
      title
      status
    }
  }
`;

export default function AddTaskButton({ reload }: {
  reload: Function;
}) {
  const [result, addTask] = useMutation(ADD_TASK);

  log('[mutation]', result);

  const handleAdd = () => {
    log('Add a new task!');
    addTask({ title: `Task-${new Date().getTime()}` });
    reload();
  };

  return (
    <button type="button" onClick={handleAdd}>
      Add new task
    </button>
  );
}
