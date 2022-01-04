# React + Apollo Client 예제

- <https://github.com/ahastudio/CodingLife/tree/main/20211008/react>
- <https://github.com/apollographql/apollo-client>
- <https://www.npmjs.com/package/@apollo/client>
- <https://apollographql.com/client>
→ <https://www.apollographql.com/docs/react/>
- <https://www.apollographql.com/docs/react/development-testing/testing/>

## npm 프로젝트 생성

```bash
mkdir <프로젝트>
cd <프로젝트>
```

```bash
npm init -y
```

## `.gitignore` 준비

```bash
touch .gitignore
```

`.gitignore` 파일 작성.

```txt
/node_modules/
/dist/
/.parcel-cache/
```

## `.eslintignore` 준비

```bash
touch .eslintignore
```

`.eslintignore` 파일 작성.

```txt
/node_modules/
/dist/
/.parcel-cache/
```

## `package.json` 스크립트 설정

```json
{
  // ...(전략)...
  "scripts": {
    "start": "parcel index.html --port 8080",
    "build": "parcel build index.html",
    "check": "tsc --noEmit",
    "lint": "eslint --fix --ext .js,.jsx,.ts,.tsx .",
    "test": "jest",
    "watch:test": "jest --watchAll"
  },
  // ...(후략)...
}
```

## `.vscode` 세팅

```bash
mkdir .vscode
touch .vscode/settings.json
```

`.vscode/settings.json` 파일 작성

```json
{
    "editor.rulers": [
        80
    ],
    "editor.codeActionsOnSave": {
        "source.fixAll.eslint": true
    },
    "trailing-spaces.trimOnSave": true
}
```

## TypeScript 세팅

```bash
npm install --save-dev typescript

npx tsc --init
```

`tsconfig.json` 파일 수정.

```json
{
  "compilerOptions": {
    // ...(전략)...
    "jsx": "react-jsx",
    // ...(후략)...
  }
}
```

## ESLint 세팅

```bash
npm install --save-dev eslint

npx eslint --init
```

```txt
? How would you like to use ESLint? …
❯ To check syntax, find problems, and enforce code style

? What type of modules does your project use? …
❯ JavaScript modules (import/export)

? Which framework does your project use? …
❯ React

? Does your project use TypeScript?
› Yes

? Where does your code run? …
✔ Browser

? How would you like to define a style for your project? …
❯ Use a popular style guide

? Which style guide do you want to follow? …
❯ Airbnb: https://github.com/airbnb/javascript

? What format do you want your config file to be in? …
❯ JavaScript

? Would you like to install them now with npm?
› Yes
```

`.eslintrc.js` 파일 수정.

통째로 베끼기:
<https://github.com/ahastudio/CodingLife/blob/main/20211008/react/.eslintrc.js>

## Jest 세팅

```bash
npm install --save-dev jest ts-jest @types/jest \
    @testing-library/react @testing-library/jest-dom

touch jest.config.js
```

`jest.config.js` 파일 작성.

```js
module.exports = {
  preset: 'ts-jest',
  testEnvironment: 'jsdom',
  setupFilesAfterEnv: [
    '@testing-library/jest-dom/extend-expect',
  ],
  testPathIgnorePatterns: [
    '<rootDir>/node_modules/',
    '<rootDir>/dist/',
  ],
};
```

## React 설치

```bash
npm install react react-dom
npm install --save-dev @types/react @types/react-dom
```

### React 기본 코드 작성

```bash
mkdir src
touch src/index.tsx
touch src/App.tsx
touch src/App.test.tsx
```

`src/App.tsx` 파일 작성.

```tsx
export default function App() {
  return null;
}
```

`src/App.test.tsx` 파일 작성.

```tsx
import { render } from '@testing-library/react';

import App from './App';

describe('App', () => {
  it('renders greeting message', () => {
    const { container } = render(<App />);

    expect(container).toHaveTextContent('Hello, world!');
  });
});
```

테스트 실행하면 실패.

```bash
npx jest
```

`src/App.tsx` 파일 수정.

```tsx
export default function App() {
  return (
    <p>Hello, world!</p>
  );
}
```

테스트 다시 실행하면 성공.

```bash
npx jest
```

`src/index.tsx` 파일 작성.

```tsx
import * as ReactDOM from 'react-dom';

import App from './App';

ReactDOM.render(<App />, document.getElementById('app'));
```

## Parcel 세팅

```bash
npm install --save-dev parcel
```

`index.html` 파일 작성.

```bash
touch index.html
```

```html
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>Sample</title>
</head>
<body>
  <div id="app">
    Loading...
  </div>
  <script type="module" src="./src/index.tsx"></script>
</body>
</html>
```

웹 서버 실행.

```bash
npx parcel index.html --port 8080
```

<http://localhost:8080/>

## 공개 GraphQL API 확인

SWAPI - The Star Wars API
<https://swapi.dev/>

GraphQL 버전:

- <https://graphql.org/swapi-graphql>
- <https://github.com/graphql/swapi-graphql>

Schema:
<https://github.com/graphql/swapi-graphql/blob/master/schema.graphql>

예제 쿼리:

```graphql
query GetAllFilms {
  allFilms {
    films {
      id
      episodeID
      title
      openingCrawl
      created
      edited
    }
  }
}
```

Endpoint: `https://swapi-graphql.netlify.app/.netlify/functions/index`

## Apollo Client 설치

```bash
npm install @apollo/client graphql
```

`src/index.tsx` 파일 수정

```tsx
import * as ReactDOM from 'react-dom';

import {
  ApolloClient,
  InMemoryCache,
  ApolloProvider,
} from '@apollo/client';

import App from './App';

const client = new ApolloClient({
  uri: 'https://swapi-graphql.netlify.app/.netlify/functions/index',
  cache: new InMemoryCache(),
});

ReactDOM.render(
  <ApolloProvider client={client}>
    <App />
  </ApolloProvider>,
  document.getElementById('app'),
);
```

## Query로 영화 목록 얻기

```bash
mkdir src/components
touch src/components/Films.tsx
touch src/components/Films.test.tsx
```

`src/components/Films.test.tsx` 파일 작성.

```tsx
import { render, waitFor } from '@testing-library/react';
import { MockedProvider } from '@apollo/client/testing';

import Films, { GET_FILMS_QUERY } from './Films';

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

describe('Films', () => {
  function Component() {
    return (
      <MockedProvider mocks={mocks} addTypename={false}>
        <Films />
      </MockedProvider>
    );
  }

  it('renders a list of films', async () => {
    const { container } = render(<Component />);

    expect(container).toHaveTextContent('Loading...');

    await waitFor(() => {
      expect(container).toHaveTextContent('A New Hope');
    });
  });
});
```

`src/components/Films.tsx` 파일 작성.

```tsx
import { gql, useQuery } from '@apollo/client';

export const GET_FILMS_QUERY = gql`
  query GetAllFilms {
    allFilms {
      films {
        id
        episodeID
        title
        openingCrawl
        created
        edited
      }
    }
  }
`;

type Film = {
  id: string;
  episodeID: number;
  title: string;
  openingCrawl: string;
  created: string;
  edited: string;
}

type AllFilms = {
  allFilms: {
    films: Film[];
  }
}

export default function Films() {
  const { loading, error, data } = useQuery<AllFilms>(GET_FILMS_QUERY);

  if (loading) {
    return <p>Loading...</p>;
  }
  if (error) {
    return <p>Error :(</p>;
  }

  const films = data?.allFilms?.films || [];

  return (
    <ul>
      {films.map((film) => (
        <li key={film.id}>
          [
          {film.episodeID}
          ]
          {' '}
          {film.title}
        </li>
      ))}
    </ul>
  );
}
```

`src/App.test.tsx` 파일 수정.

```tsx
import { render } from '@testing-library/react';

import App from './App';

jest.mock('@apollo/client', () => ({
  gql: jest.fn((x) => x),
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
import Films from './components/Films';

export default function App() {
  return (
    <div>
      <h1>Hello, world!</h1>
      <h2>Star Wars Films</h2>
      <Films />
    </div>
  );
}
```
