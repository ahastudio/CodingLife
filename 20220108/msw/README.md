# Mock Service Worker 예제

- <https://mswjs.io/>
- <https://mswjs.io/docs/getting-started/mocks/rest-api>
- <https://esbuild.github.io/getting-started/>

## MSW 세팅

```bash
npm init -y

npm i -D msw

mkdir -p src/mocks

touch src/mocks/handlers.js
touch src/mocks/server.js
touch src/mocks/index.js
```

`src/mocks/handlers.js` 파일 작성.

```js
import { rest } from 'msw';

export const handlers = [
  rest.get('https://example.com/posts', (req, res, ctx) => {
    return res(
      ctx.status(200),
      ctx.json({
        posts: [
          { id: 1, title: '1st post', body: 'Hello!' },
        ],
      }),
    );
  }),
];
```

`src/mocks/server.js` 파일 작성.

```js
import { setupServer } from 'msw/node';

import { handlers } from './handlers';

export const server = setupServer(...handlers);
```

`src/mocks/index.js` 파일 작성.

```js
import { server } from './server';

server.listen();
```

## 예제 프로그램 작성

```bash
touch src/index.js
```

`src/index.js` 파일 작성.

```js
import axios from 'axios';

import './mocks';

async function main() {
  const { data } = await axios.get(`https://example.com/posts`);

  console.log(data);
}

main();
```

## esbuild 세팅 후 빌드 및 실행

```bash
npm i -D esbuild

npx esbuild src/index.js --bundle --platform=node --outfile=out.js

node out.js
```
