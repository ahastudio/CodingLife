import Posts from '../components/Posts';
import PostForm from '../components/PostForm';

export default function HomePage() {
  return (
    <div>
      <h1>Posts</h1>
      <Posts />
      <hr />
      <PostForm />
    </div>
  );
}
