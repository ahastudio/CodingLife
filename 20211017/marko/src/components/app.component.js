import {
  fetchTasks,
  createTask,
  updateTask,
  deleteTask,
} from '../api';

export default class {
  onCreate() {
    this.state = {
      tasks: [],
    };
    this.loadTasks();
  }

  async loadTasks() {
    this.state.tasks = await fetchTasks();
  }

  async addTask({ body }) {
    if (!body) {
      return;
    }
    await createTask({ body });
    this.loadTasks();
  }

  async completeTask({ id }) {
    await updateTask({ id, status: 'done' });
    this.loadTasks();
  }

  async cancelTask({ id }) {
    await updateTask({ id, status: 'todo' });
    this.loadTasks();
  }

  async deleteTask({ id }) {
    await deleteTask({ id });
    this.loadTasks();
  }
}
