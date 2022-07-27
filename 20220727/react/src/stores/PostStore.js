import Store from './Store';

import { postService } from '../services/PostService';

export default class PostStore extends Store {
  constructor() {
    super();

    this.posts = [];
  }

  async fetchPosts() {
    this.posts = await postService.fetchPosts();

    this.publish();
  }

  async save(post) {
    const { id } = await postService.createPost(post);

    this.posts = [...this.posts, { id, ...post }];

    // await this.fetchPosts();

    this.publish();
  }
}

export const postStore = new PostStore();
