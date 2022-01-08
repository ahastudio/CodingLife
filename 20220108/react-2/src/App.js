// 1. 나만의 Fake API 서버 만들기
//    -> JSON Server 사용.
// 2. 좀 더 쉬운 API 호출
//    -> SWR
// 3. 게시물 작성
//    -> POST /posts => title, author
//    -> onClick으로 버튼 눌렸을 때 쓰일 핸들러 지정 가능.
// 4. JavaScript에서 함수를 만들 때는 function 키워드 사용.
// 5. JavaScript에서 key-value 형태의 데이터(Object)를 다룰 때는 중괄호 사용.
// 6. SWR에서 바로 다시 데이터를 얻으려면 mutate 사용.
// 7. React에서는 (JSX에서는) HTML과 다르게, 줄 넘김이 있다고 공백이 들어가진 않는다.
//    -> 공백을 표현하기 위해서는 {' '}

import Chance from 'chance';
import axios from 'axios';

import useSWR from 'swr';

import './App.css';

const chance = new Chance();

const fetcher = (url) => axios.get(url).then((response) => response.data);

function App() {
  const url = 'http://localhost:3000/posts';
  const { data: posts, mutate } = useSWR(url, fetcher);

  function handleClick() {
    axios.post(url, {
      title: chance.sentence(),
      author: chance.name(),
    }).then(() => {
      mutate();
    });
  }

  if (!posts) {
    return (
      <p>Now loading...</p>
    );
  }

  return (
    <div className="App">
      <header className="App-header">
        <h1>게시물 목록</h1>
        <ul>
          {posts.map(post => {
            return (
              <li key={post.id}>
                {post.author}
                {' '}
                -
                {' '}
                {post.title}
              </li>
            )
        })}
        </ul>
        <button type="button" onClick={handleClick}>
          게시물 추가
        </button>
      </header>
    </div>
  );
}

export default App;
