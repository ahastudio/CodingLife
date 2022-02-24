import { addPost } from '../actions/blog';

export default function Posts() {
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
