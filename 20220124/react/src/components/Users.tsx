import { useState } from 'react';

import useUsers from '../hooks/useUsers';
import useAssignUser from '../hooks/useAssignUser';

export default function Users() {
  const [state, setState] = useState({
    ticket: null,
  });

  const { users, loading, error } = useUsers();
  const { assignUser } = useAssignUser();

  if (loading) {
    return (
      <p>Loading...</p>
    );
  }

  if (error) {
    return (
      <div>
        <p>Error!</p>
        <pre>{JSON.stringify(error, undefined, 2)}</pre>
      </div>
    );
  }

  const handleClickClear = async () => {
    const ticket = await assignUser({ ticketId: 1, userId: null });
    setState({ ...state, ticket });
  };

  const handleClickUser = async (user: { id: number }) => {
    const ticket = await assignUser({ ticketId: 1, userId: user.id });
    setState({ ...state, ticket });
  };

  return (
    <div>
      <p>
        Ticket:
        {' '}
        {JSON.stringify(state.ticket)}
      </p>
      <p>
        <button type="button" onClick={handleClickClear}>
          Remove assignee
        </button>
      </p>
      <ul style={{ margin: 0, padding: 0, listStyle: 'none' }}>
        {users?.map((user) => (
          <li key={user.id} style={{ margin: '.5em 0' }}>
            <button
              type="button"
              onClick={() => handleClickUser(user)}
              style={{
                display: 'block',
                width: '20em',
                padding: '.5em',
                border: '1px solid #CCC',
                borderRadius: '.4em',
                background: 'transparent',
                textAlign: 'left',
                cursor: 'pointer',
              }}
            >
              <img
                src={user.avatar.imageUrl}
                alt="Avatar"
                style={{ width: 50, verticalAlign: 'middle' }}
              />
              {' '}
              {user.name}
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}
