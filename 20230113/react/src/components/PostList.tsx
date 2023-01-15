import { useState } from 'react';

import useFetchPosts from '../hooks/useFetchPosts';
import useCreatePost from '../hooks/useCreatePost';
import useCreatePostImmediately from '../hooks/useCreatePostImmediately';

import { Post } from '../types';

export default function PostList() {
  const posts = useFetchPosts();
  const [createdPosts, setCreatedPosts] = useState<Post[]>([]);

  // useCreatePostImmediately({ title: 'Test!', author: 'Tester' });

  const { createPost } = useCreatePost();

  const handleClick = async () => {
    const post = await createPost({ title: 'Test!', author: 'Tester' });
    if (post) {
      setCreatedPosts([...createdPosts, post]);
    }

    // Invalid hook call!
    // useCreatePostImmediately({ title: 'Test!', author: 'Tester' });
  };

  return (
    <div>
      <p>
        <button type="button" onClick={handleClick}>
          Create Post
        </button>
      </p>
      <ul>
        {[...(posts || []), ...createdPosts].reverse().map((post) => (
          <li key={post.id}>
            {JSON.stringify(post)}
          </li>
        ))}
      </ul>
    </div>
  );
}
