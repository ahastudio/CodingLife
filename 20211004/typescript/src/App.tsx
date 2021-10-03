import Greeting from './components/Greeting';

export default function App() {
  return (
    <div>
      <Greeting name="world" />
      <Greeting name="JOKER" age={13} />
    </div>
  );
}
