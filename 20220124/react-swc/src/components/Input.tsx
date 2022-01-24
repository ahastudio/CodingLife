import usePubSub from '../hooks/usePubSub';

export default function Input() {
  const { publish, value } = usePubSub();

  const handleClick = () => {
    const count = value('count') || 0;
    publish('count', count + 1);
  };

  return (
    <button type="button" onClick={handleClick}>
      Click Me!
    </button>
  );
}
