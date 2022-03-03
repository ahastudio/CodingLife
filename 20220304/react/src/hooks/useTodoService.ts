import { useContext } from 'react';

import TodoServiceContext from '../contexts/TodoServiceContext';

export default function useTodoService() {
  const todoService = useContext(TodoServiceContext);
  return todoService;
}
