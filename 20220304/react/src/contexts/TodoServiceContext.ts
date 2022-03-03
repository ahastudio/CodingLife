import { createContext } from 'react';

import TodoService from '../services/TodoService';

export const todoService = new TodoService();

const TodoServiceContext = createContext(todoService);

export default TodoServiceContext;
