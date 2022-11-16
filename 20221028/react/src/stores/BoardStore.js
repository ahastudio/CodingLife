import { Store, Action } from 'usestore-ts';

import { apiService } from '../services/ApiService';

@Store()
class BoardStore {
  posts = [];

  async fetchPosts() {
    const posts = await apiService.fetchPosts();
    this.setPosts(posts);
  }

  @Action()
  setPosts(posts) {
    this.posts = posts;
  }
}

export default BoardStore;
