export default class Ticket {
  id: string;

  title: string;

  status: string;

  constructor({ id, title, status }: {
    id: string;
    title: string;
    status: string;
  }) {
    this.id = id;
    this.title = title;
    this.status = status;
  }
}
