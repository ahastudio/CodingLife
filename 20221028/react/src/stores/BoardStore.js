import { Store, Action } from 'usestore-ts';

import { apiService } from '../services/ApiService';

@Store()
class BoardSotre {
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

export default BoardSotre;
