import { useRecoilValue } from 'recoil';

import { postsState } from '../state';

export default function Posts() {
  const posts = useRecoilValue(postsState);

  if (!posts.length) {
    return (
      <p>(Empty...)</p>
    );
  }

  return (
    <ul>
      {posts.map((post) => (
        <li key={post.id}>
          <strong>{post.title}</strong>
          <p>{post.body}</p>
        </li>
      ))}
    </ul>
  );
}
