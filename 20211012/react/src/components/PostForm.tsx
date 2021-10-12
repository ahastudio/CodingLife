import { useSetRecoilState } from 'recoil';

import { postsState } from '../state';

export default function Posts() {
  const setPosts = useSetRecoilState(postsState);

  const addPost = () => {
    setPosts((posts) => [
      {
        id: new Date().getTime(),
        title: 'What time is it?',
        body: `It's ${new Date()}`,
      },
      ...posts,
    ]);
  };

  return (
    <div>
      <button
        type="button"
        onClick={addPost}
      >
        Add post!
      </button>
    </div>
  );
}
