import { useMicroState } from '@hya/micro-state';

const { log } = console;

export default function Name() {
  log('<Name>');

  const [name] = useMicroState('name', '');

  return (
    <p>
      Name:
      {' '}
      {name}
    </p>
  );
}
