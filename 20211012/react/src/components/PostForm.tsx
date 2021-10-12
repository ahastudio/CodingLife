import { usePosts } from '../hooks';

export default function Posts() {
  const { addPost } = usePosts();

  const handleClick = () => {
    addPost();
  };

  return (
    <div>
      <button
        type="button"
        onClick={handleClick}
      >
        Add post!
      </button>
    </div>
  );
}
