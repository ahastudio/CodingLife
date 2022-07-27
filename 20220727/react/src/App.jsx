import FetchPosts from './components/FetchPosts';
import Greeting from './components/Greeting';
import PostCounter from './components/PostCounter';
import Posts from './components/Posts';
import PostForm from './components/PostForm';

export default function App() {
  return (
    <div>
      <FetchPosts />
      <Greeting />
      <PostCounter />
      <Posts />
      <PostForm />
    </div>
  );
}
