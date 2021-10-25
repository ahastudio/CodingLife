import { useStore } from '../store';

export default function Greeting() {
  const [name] = useStore.name();

  return (
    <h1>
      Hello,
      {' '}
      {name || 'world'}
      !
    </h1>
  );
}
