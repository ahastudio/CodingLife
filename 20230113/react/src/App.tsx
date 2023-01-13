import { useBoolean } from 'usehooks-ts';

import useCountRef from './hooks/useCountRef';
import useCounts from './hooks/useCounts';

import useFetchUsers from './hooks/useFetchUsers';

import useCreateUser from './hooks/useCreateUser';

function temporaryKey(index: number) {
  return `key-${index}`;
}

export default function App() {
  const { value: flag, toggle: toggleFlag } = useBoolean();

  const users = useFetchUsers();

  const { createUser } = useCreateUser();

  const count = useCountRef();

  const { counts, increase } = useCounts(5);

  console.log(`render App: ${counts}`);

  const handleClickToggle = () => {
    toggleFlag();
  };

  const handleClick = (index: number) => {
    count.current += 1;
    increase(index);
    createUser({ name: 'Mr.Hong', job: 'Tester' });
  };

  return (
    <div>
      <p>Hello, world!</p>
      <p>
        <button type="button" onClick={handleClickToggle}>
          Toggle
          {' '}
          (
          {flag ? 'On' : 'Off'}
          )
        </button>
      </p>
      <p>{count.current}</p>
      <p>{JSON.stringify(counts)}</p>
      {counts.map((_, index) => (
        <button
          key={temporaryKey(index)}
          type="button"
          onClick={() => handleClick(index)}
        >
          Click #
          {index + 1}
        </button>
      ))}
      <p>{JSON.stringify(users)}</p>
    </div>
  );
}
