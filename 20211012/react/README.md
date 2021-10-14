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

`src/state.ts` 파일 작성:

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
  // 복잡한 코드를 custom hook으로 위임.
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

## Axios 사용

목표:
가짜 API 서비스([{JSON} Placeholder](https://j.mp/3BFQuZK))를
이용해 게시물 목록을 얻어온다.

Axios 설치:

```bash
npm install axios
```

> `axios` provides its own type definitions,
> so you don't need `@types/axios` installed!

`src/hooks.tsx` 파일 수정:

```tsx
import axios from 'axios';

import { useRecoilValue, useSetRecoilState } from 'recoil';

import { postsState } from './state';

export function usePosts() {
  const posts = useRecoilValue(postsState);
  const setPosts = useSetRecoilState(postsState);

  // loadPosts 함수 추가
  const loadPosts = async () => {
    const url = 'https://jsonplaceholder.typicode.com/posts';
    const { data } = await axios.get(url);
    setPosts(data);
  };
  // ----

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
    loadPosts,
    addPost,
  };
}

// TODO: ESLint의 눈을 속이기 위한 임시 코드. 나중에 삭제할 것!
export default {};
```

`src/components/Main.tsx` 파일 수정:

```tsx
import { useEffect } from 'react';

import styled from 'styled-components';
import { usePosts } from '../hooks';

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
  const { loadPosts } = usePosts();

  // useEffect로 맨 처음에 Posts 로딩하게 함.
  useEffect(() => {
    loadPosts();
  }, []);

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

## CodeceptJS 사용

참고:
[CodeceptJS 3 시작하기
](https://github.com/ahastudio/til/blob/main/test/20201207-codeceptjs.md)

```bash
npx create-codeceptjs .
```

실행 후 `package.json` 파일의 탭이 공백 2개에서 4개로 바뀌므로
다시 되돌려줘야 함.

```bash
node -e "
  const fs = require('fs');
  const filename = 'package.json';
  const source = fs.readFileSync(filename).toString();
  const data = JSON.parse(source);
  const json = JSON.stringify(data, null, '  ');
  fs.writeFileSync(filename, json);
"
```

데모 감상:

```bash
# CLI로 확인
npm run codeceptjs:demo

# GUI로 확인
npm run codeceptjs:demo:ui
```

데모를 다 봤으니 데모에 쓰인 패키지 삭제.

```bash
npm uninstall @codeceptjs/examples
```

`pacakge.json` 파일의 `scripts` 항목에서
`codeceptjs:demo` 관련 내용 모두 삭제.

```json
  "scripts": {
    // 아래 3줄 삭제
    "codeceptjs:demo": "codeceptjs run --steps -c node_modules/@codeceptjs/examples",
    "codeceptjs:demo:headless": "HEADLESS=true codeceptjs run --steps -c node_modules/@codeceptjs/examples",
    "codeceptjs:demo:ui": "codecept-ui --app  -c node_modules/@codeceptjs/examples"
    // ----
  },
```

CodeceptJS 초기화:

```bash
npx codeceptjs init
```

```txt
? Where are your tests located? (./*_test.js)
./tests/**/*_test.ts

? What helpers do you want to use? (Use arrow keys)
❯ Playwright

? Where should logs, screenshots, and reports to be stored? (./output)
./output

? Do you want localization for tests? (See https://codecept.io/translation/) (Us
e arrow keys)
❯ English (no localization)

? [Playwright] Base url of site to be tested (http://localhost)
http://localhost:8080

? [Playwright] Show browser window (Y/n)
Y

? [Playwright] Browser in which testing will be performed. Possible options: chr
omium, firefox, webkit or electron (chromium)
chromium

? Feature which is being tested (ex: account, login, etc)
welcome

? Filename of a test (welcome_test.js)
welcome_test.ts
```

불필요한 파일 삭제:

```bash
rm jsconfig.json
rm steps_file.js
```

`codecept.conf.js` 파일에서 `include` 부분 삭제:

```javascript
  // 아래 3줄 삭제
  include: {
    I: './steps_file.js',
  },
  // ---
```

`.gitignore` 파일에 실패 기록 위치 추가:

```txt
/output/
```

[`eslint-plugin-codeceptjs`](https://github.com/poenneby/eslint-plugin-codeceptjs)
설치:

```bash
npm install --save-dev eslint-plugin-codeceptjs
```

`tests/.eslintrc.js` 파일 작성:

```javascript
module.exports = {
  extends: ['plugin:codeceptjs/recommended'],
};
```

`tests/welcome_test.js` 파일에 테스트 추가:

```javascript
Feature('welcome');

Scenario('Visit the home page', ({ I }) => {
  I.amOnPage('/');

I.see('Hello, world!');
});

Scenario('Add a new post', ({ I }) => {
  I.amOnPage('/');

  I.dontSee('What time is it?');

  I.click('Add post!');

  I.waitForText('What time is it?');
});
```

E2E 테스트 실행:

```bash
npm run codeceptjs
```
