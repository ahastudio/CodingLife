// TODO
// 1. Posts 컴포넌트가 실제로 만들어졌을 때 (mount) API 호출을 하고자 함. -> useEffect
// 2. API에서 posts 가져오기 -> Axios
// 3. 너무 복잡하다
//    1) Component 자체를 관심사에 따라 분리.
//    2) Custom Hook (useXXX)
// 4. 게시물 작성할 때 쓰는 내용들, 게시물 목록 등 데이터를 따로 관리하고 싶다.
//    -> Layered Architecture (관심사 분리)
//    -> UI Layer와 Application Layer를 분리.
//    -> Application Layer와 Domain Layer가 핵심.

import useFetchPosts from '../hooks/useFetchPosts';
import useForceUpdate from '../hooks/useForceUpdate';

export default function Posts() {
  // console.log('Update!');

  const { posts, fetchPosts } = useFetchPosts();

  const forceUpdate = useForceUpdate();

  const handleClickReload = () => {
    fetchPosts();
  };

  return (
    <div>
      <h1>Posts</h1>
      {posts.map((post) => (
        <article key={post.id}>
          <h2>
            {post.title}
            {' '}
            (by
            {' '}
            {post.author}
            )
          </h2>
          <div>
            {post.body}
          </div>
        </article>
      ))}
      <p>
        <button type="button" onClick={forceUpdate}>
          Refresh!
        </button>
      </p>
      <p>
        <button type="button" onClick={handleClickReload}>
          Reload
        </button>
      </p>
    </div>
  );
}
