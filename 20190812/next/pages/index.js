import { useState, useEffect } from 'react';
import Link from 'next/link';

import { loadPosts } from '../utils/posts';

const Home = () => {
  const [state, setState] = useState({
    posts: [],
  });

  useEffect(() => {
    (async () => {
      const posts = await loadPosts();
      setState({ ...state, posts });
    })();
  });

  const { posts } = state;

  return (
    <div>
      <ul>
        {posts.map(post =>
          <li key={post.id}>
            <Link href={`/posts/${post.id}`}><a>Post #{post.title}</a></Link>
          </li>
        )}
      </ul>
    </div>
  );
};

export default Home;
