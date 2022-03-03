import { useContext, useEffect, useState } from 'react';

import { useSetRecoilState } from 'recoil';

import TodoServiceContext from '../contexts/TodoServiceContext';

import { tasksState } from '../states/todo';

export default function StatesUpdater() {
  const [, forceUpdate] = useState(0);

  const setTasks = useSetRecoilState(tasksState);

  const todoService = useContext(TodoServiceContext);

  useEffect(() => {
    todoService.addListener(forceUpdate);
    return () => todoService.removeListener(forceUpdate);
  }, []);

  useEffect(() => {
    setTasks(todoService.tasks);
  }, [todoService.tasks]);

  return null;
}
