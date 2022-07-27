import Store from './Store';

export default class PostFormStore extends Store {
  constructor() {
    super();

    this.author = '';
    this.title = '';
    this.body = '';
  }

  changeAuthor(author) {
    this.author = author;

    this.publish();
  }

  changeTitle(title) {
    this.title = title;

    this.publish();
  }

  changeBody(body) {
    this.body = body;

    this.publish();
  }

  reset() {
    this.author = '';
    this.title = '';
    this.body = '';

    this.publish();
  }
}

export const postFormStore = new PostFormStore();
