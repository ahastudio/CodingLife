# React + Apollo Client 캐시 예제

이전 코드:
<https://github.com/ahastudio/CodingLife/tree/main/20220103/react>

- <https://www.apollographql.com/docs/react/api/react/testing/>
- <https://www.apollographql.com/docs/react/development-testing/testing/>
- <https://www.apollographql.com/docs/react/caching/cache-interaction/>

## 캐시를 조작해서 제목 바꾸기

여기선 `cache.modify`를 사용합니다.

### 테스트 코드 먼저 작성

`src/components/Films.test.tsx` 파일에
“제목 변경” 버튼 테스트 추가.

```tsx
import { render, fireEvent, waitFor } from '@testing-library/react';
import { MockedProvider } from '@apollo/client/testing';
// 추가된 부분
import { InMemoryCache } from '@apollo/client';
// -----------

import Films, { GET_FILMS_QUERY } from './Films';

// 추가된 부분
const context = describe;
// ☝️ describe-context-it 구조를 쓰기 위해 임시로 context를 추가합니다.

const cache = new InMemoryCache();
// -----------

const mocks = [
  {
    request: {
      query: GET_FILMS_QUERY,
      variables: {},
    },
    result: {
      data: {
        allFilms: {
          films: [
            {
              // 추가된 부분
              __typename: 'Film',
              // ☝️ cache를 쓸 땐 __typename을 피할 수 없네요...
              // -----------
              id: 'ID-0001',
              episodeID: 4,
              title: 'A New Hope',
// ...(중략)...

describe('Films', () => {
  function Component() {
    // 변경된 부분
    // → addTypename 대신 cache를 씁니다.
    return (
      <MockedProvider mocks={mocks} cache={cache}>
        <Films />
      </MockedProvider>
    );
    // -----------
  }

  // ...(중략)...

  // 추가된 부분
  context('when “Modify title” button is clicked', () => {
    it('changes title of a film', async () => {
      const { container, getByText } = render(<Component />);

      await waitFor(() => {
        expect(container).toHaveTextContent('A New Hope');
      });

      fireEvent.click(getByText('Modify title!'));

      await waitFor(() => {
        expect(container).toHaveTextContent('A New Hope🌟');
      });
    });
  });
  // -----------
});
```

### 테스트 통과하도록 구현

`src/components/Films.tsx` 파일에 제목 변경 버튼 추가.

```tsx
// 변경된 부분
// → useApolloClient가 추가됐습니다.
import { gql, useApolloClient, useQuery } from '@apollo/client';
// -----------

// ...(전략)...

export default function Films() {
  // 추가된 부분
  const client = useApolloClient();
  // -----------

  const { loading, error, data } = useQuery<AllFilms>(GET_FILMS_QUERY);

  // 추가된 부분
  const handleClick = (film: Film) => {
    const { cache } = client;
    cache.modify({
      id: cache.identify(film),
      // ☝️ 그냥 `Film:${film.id}`라고 직접 써줘도 됩니다.
      fields: {
        title(cachedTitle) {
          return `${cachedTitle}🌟`;
        },
        // ☝️ 여기엔 변경되는 필드만 넣어주면 됩니다.
      },
    });
  };
  // -----------

  // ...(중략)...

  return (
    <ul>
      {films.map((film) => (
        <li key={film.id}>
          [
          {film.episodeID}
          ]
          {' '}
          {film.title}
          {' '}
          {/* 추가된 부분 */}
          <button type="button" onClick={() => handleClick(film)}>
            Modify title!
          </button>
          {/* ----------- */}
        </li>
      ))}
    </ul>
  );
}
```

### App 컴포넌트 테스트 수정

`src/App.test.tsx` 파일 수정.

```tsx
import { render } from '@testing-library/react';

import App from './App';

jest.mock('@apollo/client', () => ({
  gql: jest.fn((x) => x),
  // 추가된 부분
  useApolloClient: jest.fn(),
  // -----------
  useQuery: jest.fn(() => ({
    loading: false,
    error: undefined,
    data: {
      allFilms: {
        films: [
          // TODO: if you want, fill this place.
        ],
      },
    },
  })),
}));

describe('App', () => {
  it('renders greeting message', () => {
    const { container } = render(<App />);

    expect(container).toHaveTextContent('Hello, world!');
  });
});
```

`src/App.tsx` 파일 수정.

```tsx
import AddFilm from './components/AddFilm';
import Films from './components/Films';

export default function App() {
  return (
    <div>
      <h1>Hello, world!</h1>
      <h2>Star Wars Films</h2>
      <Films />
      <AddFilm />
    </div>
  );
}
```

## 캐시를 조작해서 필름 추가하기

여기선 `cache.updateQuery`를 사용합니다.

### 이번에도 테스트 코드 먼저 작성

`src/components/AddFilm.test.tsx` 파일 작성.

```tsx
import { render, fireEvent } from '@testing-library/react';
import { MockedProvider } from '@apollo/client/testing';
import { InMemoryCache } from '@apollo/client';

import AddFilm from './AddFilm';
import { GET_FILMS_QUERY } from './Films';

const context = describe;

const cache = new InMemoryCache();

const mocks = [
  {
    request: {
      query: GET_FILMS_QUERY,
      variables: {},
    },
    result: {
      data: {
        allFilms: {
          films: [
            {
              __typename: 'Film',
              id: 'ID-0001',
              episodeID: 4,
              title: 'A New Hope',
              openingCrawl: 'It is a period of civil war. blah blah',
              created: '2014-12-10T14:23:31.880000Z',
              edited: '2014-12-20T19:49:45.256000Z',
            },
          ],
        },
      },
    },
  },
];

describe('AddFilm', () => {
  function Component() {
    return (
      <MockedProvider mocks={mocks} cache={cache}>
        <AddFilm />
      </MockedProvider>
    );
  }

  it('renders “Add film” button', async () => {
    const { container } = render(<Component />);

    expect(container).toHaveTextContent('Add film');
  });

  context('when Add film” button is clicked', () => {
    const [mock] = mocks;

    beforeEach(() => {
      const { query } = mock.request;
      cache.updateQuery({ query }, () => mock.result.data);
    });

    it('changes title of a film', async () => {
      const { getByText } = render(<Component />);

      fireEvent.click(getByText('Add film'));

      const data: any = cache.readQuery({ query: GET_FILMS_QUERY });

      expect(data.allFilms.films)
        .toHaveLength(mock.result.data.allFilms.films.length + 1);
    });
  });
});
```

### 그 다음에 테스트 통과하도록 구현

`src/components/AddFilm.tsx` 파일 작성.

```tsx
import { useApolloClient } from '@apollo/client';

import { GET_FILMS_QUERY } from './Films';

let newId = 10;

function generateFilm() {
  const now = new Date();

  newId += 1;

  return {
    id: newId,
    episodeID: new Date().getTime(),
    title: `Title - ${now}`,
    openingCrawl: '',
    created: now,
    edited: now,
  };
}

export default function AddFilm() {
  const client = useApolloClient();

  const handleClick = () => {
    const newFilm = generateFilm();

    const { cache } = client;
    cache.updateQuery({
      query: GET_FILMS_QUERY,
    }, (data) => ({
      ...data,
      allFilms: {
        ...data.allFilms,
        films: [...data.allFilms.films, {
          __typename: 'Film',
          ...newFilm,
        }],
      },
    }));
  };

  return (
    <div>
      <button type="button" onClick={handleClick}>
        Add film
      </button>
    </div>
  );
}
```

### App 컴포넌트을 수정해서 버튼 추가

`src/App.tsx` 파일 수정.

```tsx
import Films from './components/Films';
import AddFilm from './components/AddFilm';

export default function App() {
  return (
    <div>
      <h1>Hello, world!</h1>
      <h2>Star Wars Films</h2>
      <Films />
      <AddFilm />
    </div>
  );
}
```
