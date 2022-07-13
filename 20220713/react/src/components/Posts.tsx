import usePosts from '../hooks/usePosts';

export default function Posts() {
  const posts = usePosts();

  if (!posts?.length) {
    return null;
  }

  return (
    <div>
      {posts.map((post) => (
        <article key={post.id}>
          <h2>{post.title}</h2>
          <div>{post.body}</div>
        </article>
      ))}
    </div>
  );
}
