# Ticket Assignment Demo

기본 코드:
<https://github.com/ahastudio/CodingLife/tree/main/20211008/react>

참고:
<https://github.com/ahastudio/til/blob/main/graphql/graphql-request.md>

※ Apollo Client와 API 사용법이 다른 점에 주의하세요.

## GraphQL 라이브러리 세팅

`graphql-request` 패키지 설치.

```bash
npm i graphql-request graphql
```

`client.ts` 파일 작성.

```bash
touch src/client.ts
```

```ts
import { GraphQLClient } from 'graphql-request';

const client = new GraphQLClient('http://localhost:4000/graphql');

export default client;
```

## `useUsers` 훅

```bash
mkdir -p src/hooks
touch src/hooks/useUsers.ts
```

`useUsers.ts` 파일 작성.

```ts
import { gql } from 'graphql-request';

import { useEffect, useState } from 'react';

import client from '../client';

const USERS_QUERY = gql`
  query UsersQuery {
    users {
      id
      role
      name
      avatar {
        id
        imageUrl
      }
    }
  }
`;

export default function useUsers() {
  const [users, setUsers] = useState<any[] | null>(null);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<any>(undefined);

  const loadUsers = async () => {
    try {
      const data = await client.request(USERS_QUERY);
      setUsers(data.users);
      setLoading(false);
    } catch (e) {
      setLoading(false);
      setError(e);
    }
  };

  useEffect(() => {
    loadUsers();
  }, []);

  return { users, loading, error };
}
```

## `Users` 컴포넌트

```bash
mkdir -p src/components
touch src/components/Users.tsx
touch src/components/Users.test.tsx
```

테스트 코드 작성.

```tsx
import { render } from '@testing-library/react';

import Users from './Users';

// Just Hack! :)
const context = describe;

let data: {
  users: any[] | null;
  loading: boolean;
  error?: any;
};

jest.mock('../hooks/useUsers', () => jest.fn(() => data));

describe('Users', () => {
  context('when loading is complete', () => {
    beforeEach(() => {
      data = {
        users: [
          { id: 1, name: 'Peter Parker' },
        ],
        loading: false,
      };
    });

    it('renders a list of user', () => {
      const { container } = render(<Users />);

      expect(container).toHaveTextContent('Peter Parker');
    });
  });

  context('when loading is not complete', () => {
    beforeEach(() => {
      data = {
        users: null,
        loading: true,
      };
    });

    it('renders loading message', () => {
      const { container } = render(<Users />);

      expect(container).toHaveTextContent('Loading...');
    });
  });

  context('when error occurs', () => {
    beforeEach(() => {
      data = {
        users: null,
        loading: false,
        error: true,
      };
    });

    it('renders error message', () => {
      const { container } = render(<Users />);

      expect(container).toHaveTextContent('Error!');
    });
  });
});
```

컴포넌트 구현.

```tsx
import useUsers from '../hooks/useUsers';

export default function Users() {
  const { users, loading, error } = useUsers();

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

  return (
    <ul>
      {users?.map((user) => (
        <li key={user.id}>
          {user.name}
        </li>
      ))}
    </ul>
  );
}
```

## Mutation

`useAssignUser.tsx` 파일 작성.

```bash
touch src/hooks/useAssignUser.ts
```

```ts
import { gql } from 'graphql-request';

import client from '../client';

const CHANGE_TICKET_ASSIGNEE = gql`
  mutation ChangeTicketAssignee($id: Int, $userId: Int) {
    changeTicketAssignee(id: $id, userId: $userId) {
      id
      assignee {
        user {
          id
          name
          avatar {
            imageUrl
          }
        }
      }
    }
  }
`;

export default function useAssignUser() {
  return {
    async assignUser({ ticketId, userId }: {
      ticketId: number;
      userId: number;
    }): Promise<any> {
      const data = await client.request(CHANGE_TICKET_ASSIGNEE, {
        id: ticketId,
        userId,
      });
      return data.changeTicketAssignee;
    },
  };
}
```

`Users.tsx` 파일 수정.

```tsx
import useUsers from '../hooks/useUsers';
import useAssignUser from '../hooks/useAssignUser';

const { log } = console;

export default function Users() {
  // ...(중략)...

  const handleClick = ({ user }: {
    user: { id: number }
  }) => {
    assignUser({ ticketId: 1, userId: user.id });
  };

  return (
    <ul>
      {users?.map((user) => (
        <li key={user.id}>
          {user.name}
          {' '}
          <button type="button" onClick={() => handleClick({ user })}>
            Assign
          </button>
        </li>
      ))}
    </ul>
  );
}
```

## 최종 코드

`Users.test.tsx`에 테스트 코드 추가된 부분이나
다른 세부적인 사항은 최종 코드를 확인하세요.
