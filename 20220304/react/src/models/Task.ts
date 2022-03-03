export default class Task {
  id: string;

  title: string;

  completed: boolean;

  constructor({ id, title, completed }: {
    id: string;
    title: string;
    completed?: boolean;
  }) {
    this.id = id;
    this.title = title;
    this.completed = !!completed;
  }

  toggle() {
    this.completed = !this.completed;
  }
}
