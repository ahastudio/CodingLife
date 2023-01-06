import axios from 'axios';

export default function useDeletePost() {
  return {
    async deletePost(id) {
      await axios.delete(`http://localhost:8080/posts/${id}`);
    },
  };
}
