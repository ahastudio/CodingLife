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
// 5. UI Layer = React Component와 연관된/분리된 Store 객체를 마련.
//    Store -> 상태를 저장/변경 <- Action을 통해서...(= method)
//    e.g.) store.reload();
//          store.loadPosts();
//          store.createPost();
//    React Component -> Store의 상태가 바뀌었는지 감시.
//                    -> 상태 변경에 대한 걸 구독.
//                    -> 상태가 바뀌면 force update!
//    Pub(Store)-Sub(View=React)
// 6. 글 쓰기
//    - PostForm (component)
//    - PostFormStore (store)
//    - PostStore -> save, reload(또는 임의로 추가)

import usePostStore from '../hooks/usePostStore';

export default function Posts() {
  // console.log('Update!');

  const postStore = usePostStore();

  const { posts } = postStore;

  const handleClickReload = async () => {
    await postStore.fetchPosts();
  };

  return (
    <div>
      <h1>Posts</h1>
      <p>
        <button type="button" onClick={handleClickReload}>
          Reload
        </button>
      </p>
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
    </div>
  );
}
