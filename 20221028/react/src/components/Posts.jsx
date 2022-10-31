import useBoardStore from '../hooks/useBoardStore';

export default function Posts() {
  const [{ posts }] = useBoardStore();

  if (!posts.length) {
    return (
      <p>게시물이 없습니다</p>
    );
  }

  return (
    <ul>
      {posts.map((post) => (
        <li key={post.id}>
          <div>
            {post.title}
            {' '}
            by
            {' '}
            {post.author}
          </div>
          <div>
            {post.body}
          </div>
        </li>
      ))}
    </ul>
  );
}
