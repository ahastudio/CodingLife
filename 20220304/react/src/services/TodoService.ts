import { uniqueId } from 'lodash';

import Task from '../models/Task';

export default class TodoService {
  state: {
    tasks: Task[];
  };

  listeners: Set<Function>;

  constructor() {
    this.state = {
      tasks: [],
    };
    this.listeners = new Set();
  }

  get tasks() {
    return this.state.tasks;
  }

  set tasks(tasks) {
    this.state.tasks = tasks;

    this.listeners.forEach((listener) => listener({ ...this.state }));
  }

  addListener(callback: Function) {
    this.listeners.add(callback);
  }

  removeListener(callback: Function) {
    this.listeners.delete(callback);
  }

  clear() {
    this.tasks = [];
  }

  empty() {
    return this.tasks.length === 0;
  }

  addTask(title: string) {
    const task = new Task({
      id: uniqueId(),
      title,
    });
    this.tasks = [...this.tasks, task];
  }

  removeTask(id: string) {
    this.tasks = this.tasks.filter((task) => task.id !== id);
  }

  toggleTask(id: string) {
    this.tasks = this.tasks.map((task) => {
      if (task.id === id) {
        task.toggle();
      }
      return task;
    });
  }
}
