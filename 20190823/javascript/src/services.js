import { Counter } from './utils/counter';
import axios from 'axios';

const initialState = {
  tasks: [],
};

const counter = Counter();

const addTask = (state, title) => {
  const { tasks } = state;
  return {
    ...state,
    tasks: [...tasks, { id: counter(), title, completed: false }],
  };
};

const toggleTask = (state, taskId) => {
  const { tasks } = state;
  return {
    ...state,
    tasks: tasks.map(task => ({
      ...task,
      ...(task.id === taskId ? { completed: !task.completed } : {})
    })),
  };
};

export {
  initialState,
  addTask,
  toggleTask,
}
