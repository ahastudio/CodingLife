import axios from 'axios';

export default class PostService {
  instance = axios.create({
    baseURL: 'http://localhost:3000',
  });

  async fetchPosts() {
    // console.log('fetchPosts');
    const { data } = await this.instance.get('/posts');
    return data;
  }

  async createPost(post) {
    const { data } = await this.instance.post('/posts', post);
    return data;
  }
}

export const postService = new PostService();
