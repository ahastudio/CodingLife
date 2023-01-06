import axios from 'axios';

export default function useCreatePost() {
  return {
    async createPost({ title, content }) {
      await axios.post('http://localhost:8080/posts', { title, content });
    },
  };
}
