// 1. 게시물 작성 폼 추가
//    - author: 내가 누구인가?
//    - title: 어떤 제목인가?
//    - body: 어떤 내용인가?
// 2. <input> => type, name, value ... => onChange
//    -> 어떤 내용을 담고 있는지 관리.
// 3. React Hook Form으로 단순화하자!
//    -> useForm Hook을 활용.
//    -> Spread Syntax를 활용.
//    -> <form>에 대해 onSubmit 이벤트 처리 필요함.
//       => 폼에 입력된 데이터를 객체(key-value 쌍 모음)로 얻을 수 있음.

import axios from 'axios';

import useSWR from 'swr';
import { useForm } from 'react-hook-form';

import './App.css';

const fetcher = (url) => axios.get(url).then((response) => response.data);

function App() {
  const url = 'http://localhost:3000/posts';
  const { data: posts, mutate } = useSWR(url, fetcher);

  const { register, handleSubmit } = useForm();

  function handleSubmitForm(data) {
    axios.post(url, data).then(() => mutate());
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
                <div>
                  {post.title}
                  {' '}
                  by
                  {' '}
                  {post.author}
                </div>
                <div>
                  {post.body}
                </div>
              </li>
            )
        })}
        </ul>
        <form onSubmit={handleSubmit(handleSubmitForm)}>
          <div>
            <label htmlFor="input-author">작성자:</label>
            <input id="input-author" {...register('author')} />
          </div>
          <div>
            <label htmlFor="input-title">제목:</label>
            <input id="input-title" {...register('title')} />
          </div>
          <div>
            <label htmlFor="input-body">본문:</label>
            <textarea id="input-body" {...register('body')} />
          </div>
          <button type="submit">
            게시물 추가
          </button>
        </form>
      </header>
    </div>
  );
}

export default App;
