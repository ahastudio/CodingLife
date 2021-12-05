import useChange from 'use-change';

import store from '../store';

export default function NewTask() {
  const [newTask] = useChange(store, 'newTask');

  if (!newTask.title) {
    return null;
  }

  return (
    <p>
      Do you want?
      {' '}
      “
      {newTask.title}
      ”
    </p>
  );
}
