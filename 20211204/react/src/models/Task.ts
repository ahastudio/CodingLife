export default class Task {
  public id: number;

  public title: string;

  constructor({ id, title }: {
    id?: number;
    title?: string;
  } = {}) {
    this.id = id || new Date().getTime();
    this.title = title || '';
  }

  updateTitle(title: string) {
    return new Task({ id: this.id, title });
  }
}
