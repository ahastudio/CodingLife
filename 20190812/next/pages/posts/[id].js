import { useState, useEffect } from 'react';
import { useRouter } from 'next/router';
import Link from 'next/link';

import { loadPost } from '../../utils/posts';

const PostContent = ({ post }) => (
  <>
    <h2>{post.title}</h2>
    <div>
      {post.body}
    </div>
  </>
);

const Post = () => {
  const router = useRouter();
  const { id } = router.query;

  const [state, setState] = useState({
    post: null,
  });

  useEffect(() => {
    (async () => {
      const post = await loadPost(id);
      setState({ ...state, post });
    })();
  });

  const { post } = state;

  return (
    <div>
      {post ? <PostContent post={post} /> : <p>Loading...</p>}
      <hr />
      <Link href="/"><a>List</a></Link>
    </div>
  );
};

export default Post;
