import useFetchPosts from '../hooks/useFetchPosts';
import useDeletePost from '../hooks/useDeletePost';

export default function Posts() {
  const { loading, error, posts } = useFetchPosts();
  const { deletePost } = useDeletePost();

  const handleClickDelete = async (id) => {
    await deletePost(id);
    window.location.reload();
  };

  if (loading) {
    return (
      <p>로딩 중...</p>
    );
  }

  if (error) {
    return (
      <div>
        <p>에러!</p>
        <code>{JSON.stringify(error)}</code>
      </div>
    );
  }

  if (!posts.length) {
    return (
      <p>게시물이 없습니다</p>
    );
  }

  return (
    <ul>
      {posts.map((post) => (
        <li key={post.id}>
          <article>
            <h2>
              {post.title}
            </h2>
            <div>
              {post.content}
            </div>
            <div>
              <button type="button" onClick={() => handleClickDelete(post.id)}>
                삭제
              </button>
            </div>
          </article>
        </li>
      ))}
    </ul>
  );
}
