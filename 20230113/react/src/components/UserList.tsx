import { useEffect } from 'react';

import useFetchUsers from '../hooks/useFetchUsers';
import useCreateUser from '../hooks/useCreateUser';
// import useCreateUserImmediately from '../hooks/useCreateUserImmediately';

export default function UserList() {
  const users = useFetchUsers();

  useEffect(() => {
    // Invalid hook call!
    // useCreateUserImmediately({ name: 'Mr.Hong', job: 'Tester' });
  });

  const { createUser } = useCreateUser();

  const handleClick = () => {
    createUser({ name: 'Mr.Hong', job: 'Tester' });

    // Invalid hook call!
    // useCreateUserImmediately({ name: 'Mr.Hong', job: 'Tester' });
  };

  return (
    <div>
      <ul>
        {users.map((user) => (
          <li key={user.id}>
            {JSON.stringify(user)}
          </li>
        ))}
      </ul>
      <p>
        <button type="button" onClick={handleClick}>
          Create User
        </button>
      </p>
    </div>
  );
}
