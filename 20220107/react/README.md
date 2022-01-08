# Create React App 예제

## 사용하는 도구

Create React App

- <https://create-react-app.dev/>
- <https://github.com/facebook/create-react-app>

SWR

- <https://swr.vercel.app/ko>
- <https://github.com/vercel/swr>

Axios

- <https://axios-http.com/>
- <https://github.com/axios/axios>

Chance

- <https://chancejs.com/>
- <https://github.com/chancejs/chancejs>

lorem-ipsum

- <https://npm.im/lorem-ipsum>
- <https://github.com/knicklabs/lorem-ipsum.js>

## CRA 프로젝트 세팅

```bash
npx clear-npx-cache

npx create-react-app my-app

cd my-app
```

`.env` 파일를 이용해 환경 변수 지정.
포트를 `8080`으로 바꾸고, 웹 브라우저를 자동으로 열지 않게 함.
([Advanced Configuration](https://create-react-app.dev/docs/advanced-configuration/)
문서 참고)

```bash
echo 'PORT=8080\nBROWSER=none' > .env
```

웹 서버 시작:

```bash
npm start
```

<http://localhost:8080/>

## SWR 적용

SWR, Axios 설치.

```bash
npm install swr axios
```

`src/App.js` 파일에 코드 추가.

```jsx
import useSWR from 'swr';
import axios from 'axios';

// ...(중략)...

const fetcher = (url) => axios.get(url).then((response) => response.data);

function App() {
  const url = 'http://localhost:3000/posts';
  const { data: posts } = useSWR(url, fetcher);

  if (!posts) {
    return <p>Loading...</p>;
  }

  return (
    <div className="App">
      <header className="App-header">
        {/* ...(중략)... */}
        <h1>Posts</h1>
        <ul>
          {posts.map((post) => (
            <li key={post.id}>
              {post.title}
              {' '}
              by {post.author}
            </li>
          ))}
        </ul>
      </header>
    </div>
  );
}

export default App;
```

`src/App.css` 파일에 적절한 스타일 추가.

```css
.App-header h1 {
  font-size: 1.5em;
  margin: 2em 0 0;
}

.App-header ul {
  padding: 1em 1em 1em 2em;
  border-radius: 4px;
  background: rgba(0, 0, 0, .3);
  text-align: left;
}
```

## 게시물 추가

Chance, Lorem Ipsum 패키지 설치.

```bash
npm i chance lorem-ipsum
```

`src/App.js` 파일에 코드 추가.

```jsx
import useSWR from 'swr';
import axios from 'axios';
import Chance from 'chance';
import { loremIpsum } from 'lorem-ipsum';

// ...(중략)...

function App() {
  const url = 'http://localhost:3000/posts';
  const { data: posts, mutate } = useSWR(url, fetcher);

  if (!posts) {
    return <p>Loading...</p>;
  }

  function addPost() {
    const chance = new Chance();
    axios.post(url, {
      author: chance.name(),
      title: chance.animal(),
      body: loremIpsum(),
    }).then(() => mutate());
  }

  return (
    <div className="App">
      <header className="App-header">
        {/* ...(중략)... */}
        <button type="button" onClick={addPost}>
          게시물 추가
        </button>
      </header>
    </div>
  );
}

export default App;
```

`src/App.css` 파일에 적절한 스타일 추가.

```css
.App-header button {
  font-size: 1em;
  margin-bottom: 5em;
  padding: .5em 1em;
  border: 0;
  border-radius: 2em;
  box-shadow: 2px 4px 8px rgba(0, 0, 0, 0.8);
  cursor: pointer;
}
```
