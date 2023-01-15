import Greeting from './components/Greeting';
import TimerControl from './components/TimerControl';
import Counter from './components/Counter';
import PostList from './components/PostList';

export default function App() {
  return (
    <div>
      <Greeting />
      <TimerControl />
      <Counter />
      <PostList />
    </div>
  );
}
