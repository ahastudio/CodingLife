import { useRecoilValue, useSetRecoilState } from 'recoil';

import { postsState } from './state';

export function usePosts() {
  const posts = useRecoilValue(postsState);
  const setPosts = useSetRecoilState(postsState);

  const addPost = () => {
    setPosts((oldPosts) => [
      {
        id: new Date().getTime(),
        title: 'What time is it?',
        body: `It's ${new Date()}`,
      },
      ...oldPosts,
    ]);
  };

  return {
    posts,
    addPost,
  };
}

// TODO: ESLint의 눈을 속이기 위한 임시 코드. 나중에 삭제할 것!
export default {};
