export default class {
  completeTask() {
    const { id } = this.input.task;
    this.emit('task-event', 'complete-task', { id });
  }

  cancelTask() {
    const { id } = this.input.task;
    this.emit('task-event', 'cancel-task', { id });
  }

  deleteTask() {
    const { id } = this.input.task;
    this.emit('task-event', 'delete-task', { id });
  }
}
