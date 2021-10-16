import axios from 'axios';

import { useRecoilState } from 'recoil';

import { postsState } from './state';

export function usePosts() {
  const [posts, setPosts] = useRecoilState(postsState);

  const loadPosts = async () => {
    const url = 'https://jsonplaceholder.typicode.com/posts';
    const { data } = await axios.get(url);
    setPosts(data);
  };

  const addPost = () => {
    setPosts([
      {
        id: new Date().getTime(),
        title: 'What time is it?',
        body: `It's ${new Date()}`,
      },
      ...posts,
    ]);
  };

  return {
    posts,
    loadPosts,
    addPost,
  };
}

// TODO: ESLint의 눈을 속이기 위한 임시 코드. 나중에 삭제할 것!
export default {};
