import useChange from 'use-change';

import store from '../store';

import Task from '../models/Task';

export default function useTasks() {
  const [tasks, setTasks] = useChange(store, 'tasks');
  const [newTask, setNewTask] = useChange(store, 'newTask');

  const addTask = () => {
    setTasks([newTask, ...tasks]);
    setNewTask(new Task());
  };

  const removeTask = (id: number) => {
    setTasks(tasks.filter((i) => i.id !== id));
  };

  const updateNewTaskTitle = (title: string) => {
    setNewTask(newTask.updateTitle(title));
  };

  return {
    tasks,
    newTask,
    addTask,
    removeTask,
    updateNewTaskTitle,
  };
}
