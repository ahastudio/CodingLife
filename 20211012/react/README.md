# React 프로젝트 예제

기존 코드:
<https://github.com/ahastudio/CodingLife/tree/main/20211008/react>

## Parcel 대신 esbuild 사용

- <https://github.com/evanw/esbuild>
- <https://github.com/lukejacksonn/servor>
- <https://github.com/open-cli-tools/concurrently>

Parcel 제거:

```bash
npm uninstall parcel

rm -rf .parcel-cache/
```

esbuild, Servør, Concurrently 설치:

```bash
npm install --save-dev esbuild servor concurrently
```

`tsconfig.json` 파일 수정:

```json
{
  // ...(전략)...
    "target": "es6",
  // ...(후략)...
}
```

`react-shim.js` 파일 작성:

```javascript
/* eslint-disable import/prefer-default-export */

import * as React from 'react';

export { React };
```

`package.json` 파일 수정:

```json
{
  "scripts": {
    "start": "npm run build && concurrently 'npm:serve' 'npm:watch-js'",
    "serve": "servor dist index.html 8080 --reload",
    "build": "mkdir -p dist && cp index.html dist/ && npm run build-js",
    "build-js": "esbuild ./src --bundle --inject:./react-shim.js --outfile=dist/bundle.js",
    "watch-js": "npm run build-js -- --watch",
    // ...(후략)...
  }
}
```

## styled-components 사용

[styled-components](https://github.com/styled-components/styled-components)
설치.

```bash
npm install styled-components
npm install --save-dev @types/styled-components
```

Visual Studio Code에
“[vscode-styled-components](https://j.mp/3AzM3hF)”
Extension 설치.

설치 후 화면:

<img src="https://github.com/styled-components/vscode-styled-components/raw/HEAD/demo.gif" alt="demo" width="617" />

`src/App.tsx` 수정:

```tsx
import styled from 'styled-components';

const Greeting = styled.p`
  font-size: 2em;
  text-align: center;
  i {
    font-size: 5em;
  }
`;

export default function App() {
  return (
    <Greeting>
      Hello, world
      <i>!</i>
    </Greeting>
  );
}
```

## Recoil 사용

Recoil 설치:

```bash
npm install recoil
npm install --save-dev @types/recoil
```

`src/components/Main.tsx` 파일로 컨텐츠 이동:

```tsx
import styled from 'styled-components';

const Greeting = styled.p`
  font-size: 2em;
  text-align: center;
  i {
    font-size: 5em;
  }
`;

export default function Main() {
  return (
    <Greeting>
      Hello, world
      <i>!</i>
    </Greeting>
  );
}
```

`src/App.tsx` 수정:

```tsx
import { RecoilRoot } from 'recoil';

import Main from './components/Main';

export default function App() {
  return (
    <RecoilRoot>
      <Main />
    </RecoilRoot>
  );
}
```

```src/state.ts` 파일 작성:

```ts
import { atom } from 'recoil';

export interface Post {
  id: number;
  title: string;
  body: string;
}

export const postsState = atom<Post[]>({
  key: 'postsState',
  default: [],
});
```

`src/components/Posts.tsx` 파일 작성:

```tsx
import { useRecoilValue } from 'recoil';

import { postsState } from '../state';

export default function Posts() {
  const posts = useRecoilValue(postsState);

  if (!posts.length) {
    return (
      <p>(Empty...)</p>
    );
  }

  return (
    <ul>
      {posts.map((post) => (
        <li key={post.id}>
          <strong>{post.title}</strong>
          <p>{post.body}</p>
        </li>
      ))}
    </ul>
  );
}
```

`src/components/PostForm.tsx` 파일 작성:

```tsx
import { useSetRecoilState } from 'recoil';

import { postsState } from '../state';

export default function Posts() {
  const setPosts = useSetRecoilState(postsState);

  const addPost = () => {
    setPosts((posts) => [
      {
        id: new Date().getTime(),
        title: 'What time is it?',
        body: `It's ${new Date()}`,
      },
      ...posts,
    ]);
  };

  return (
    <div>
      <button
        type="button"
        onClick={addPost}
      >
        Add post!
      </button>
    </div>
  );
}
```

`src/components/Main.tsx` 파일 수정:

```tsx
import styled from 'styled-components';

import PostForm from './PostForm';
import Posts from './Posts';

const Greeting = styled.p`
  font-size: 2em;
  text-align: center;
  i {
    font-size: 5em;
  }
`;

export default function Main() {
  return (
    <div>
      <Greeting>
        Hello, world
        <i>!</i>
      </Greeting>
      <PostForm />
      <Posts />
    </div>
  );
}
```

## Custom Hook 사용

목표:

1. 개별 컴포넌트에서 Recoil을 직접 사용하지 않게 한다(감춘다).
1. 게시물을 추가하는 작업을 `addPost` 형태로 추상화한다.

[자신만의 Hook 만들기](https://ko.reactjs.org/docs/hooks-custom.html)
문서 참고.

`src/hooks.tsx` 파일 작성:

```tsx
import { useRecoilValue, useSetRecoilState } from 'recoil';

import { postsState } from './state';

export function usePosts() {
  const posts = useRecoilValue(postsState);
  const setPosts = useSetRecoilState(postsState);

  const addPost = () => {
    setPosts((oldPosts) => [
      {
        id: new Date().getTime(),
        title: 'What time is it?',
        body: `It's ${new Date()}`,
      },
      ...oldPosts,
    ]);
  };

  return {
    posts,
    addPost,
  };
}

// TODO: ESLint의 눈을 속이기 위한 임시 코드. 나중에 삭제할 것!
export default {};
```

`src/PostForm.tsx` 파일 수정:

```tsx
import { usePosts } from '../hooks';

export default function Posts() {
  const { addPost } = usePosts();

  const handleClick = () => {
    addPost();
  };

  return (
    <div>
      <button
        type="button"
        onClick={handleClick}
      >
        Add post!
      </button>
    </div>
  );
}
```

`src/Posts.tsx` 파일 수정:

```tsx
import { usePosts } from '../hooks';

export default function Posts() {
  const { posts } = usePosts();

  if (!posts.length) {
    return (
      <p>(Empty...)</p>
    );
  }

  return (
    <ul>
      {posts.map((post) => (
        <li key={post.id}>
          <strong>{post.title}</strong>
          <p>{post.body}</p>
        </li>
      ))}
    </ul>
  );
}
```
