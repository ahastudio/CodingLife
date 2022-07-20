import useUserStore from '../hooks/useUserStore';

export default function Greeting() {
  const [{ name }] = useUserStore();

  return (
    <div>
      <p>
        Hello,
        {' '}
        {name.value || 'world'}
        !
      </p>
    </div>
  );
}
