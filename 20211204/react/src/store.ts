import Task from './models/Task';

export class RootStore {
  public tasks: Task[] = [];

  public newTask: Task = new Task();
}

export default new RootStore();
