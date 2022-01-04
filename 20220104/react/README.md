# React + Apollo Client 캐시 예제

이전 코드:
<https://github.com/ahastudio/CodingLife/tree/main/20220103/react>

- <https://www.apollographql.com/docs/react/api/react/testing/>
- <https://www.apollographql.com/docs/react/development-testing/testing/>
- <https://www.apollographql.com/docs/react/caching/cache-interaction/>

## 캐시를 조작해서 제목 바꾸기

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
