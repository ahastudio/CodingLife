// 1. Create React App (CRA) 도구로 기본적인 앱 만들기.
// 2. 웹 서버 실행하기.
// 3. 파일 수정해서 실시간으로 반영되는 것 확인.
// 4. F/E에서 B/E에 요청하기.
//    Web -> 요청(입력) -> 처리 -> 응답(출력) => Web Server의 관점
//    1) HTML을 Java로 만든 서버에서 직접 주기
//    2) HTML을 구성하는 건 React가 하고, Java로 만든 서버는 JSON 포맷으로 응답.
//       -> JSON은 객체를 평문(일반 텍스트)으로 전달할 수 있게 만드는 수단.
//       -> JPEG(jpg)는 이미지/사진을 파일의 형태로 전달할 수 있게 하는 수단.
// 5. JSON으로 뭔가 응답 받기
//    -> 누구에게? -> B/E, API 서버에게 요청해서 응답을 받는다.
//    => REST API 요청 (HTTP 요청)
//       CRUD (Create, Read, Update, Delete) -> 어떤 자원에 대해서 수행.
//       Post (게시물) => 자원(Resource)
//       - Create: 글 쓰기 => POST /posts
//       - Read: 게시물 목록 얻기(복수), (특정) 게시물 내용 보기(단수)
//         => GET /posts, GET /posts/{id} 또는 GET /posts/:id
//       - Update: (특정) 게시물 수정 => PUT /posts/{id}, PATCH /posts/{id}
//       - Delete: (특정) 게시물 삭제 => DELETE /posts/{id}
//       => 전부 하나의 URL로 처리.
//          단, 특정 아이템을 지정할 때는 ID(identifier)를 추가로 써준다.
//       e.g. /posts -> 하나의 주소 => 게시물(들). <복수>
//            /posts/1 -> ID가 1인 게시물. <단수>
//       => HTTP Method로 각 행위/연산/처리/액션을 구분한다.
//    -> 어떻게? => Axios 라이브러리 (fetch API가 기본으로 있지만, 쓰기 번거롭다.)
// 6. 무식하게 게시물 목록 얻기
//    -> JavaScript는 타입 지정을 하지 않는다. 다만, 어떤 식으로 다룰지 지정한다.
//    -> Java의 일반 변수(mutable = 가변), 상수(final, immutable = 불변)을 지정.
//    -> JavaScript에선 let와 const로 표현. => 가능하면 const 사용.
// 7. JavaScript에선 문자열을 표현할 때 큰 따옴표를 쓸 수도 있고, 작은 따옴표를 쓸 수도 있다.
// 8. 콘솔로 확인하려면 console.log 사용. System.out.println과 유사하다.
// 9. JavaScript에서는 비동기로 뭔가를 처리할 수 있고, Promise란 걸 활용.
//    -> then...
// 10. React의 State란 걸 활용.
//     -> useState란 Hook으로 처리.
//     -> 초기값 지정 => 현재 값을 알 수 있는 변수와 상태를 바꿀 수 있는 함수를 돌려줌.
//     -> 돌려준 값을 destructuring해서 사용. [a, b] = f();
// 11. JavaScript에서는 비어 있는 Array(List<>와 유사)를 []로 표현.
//     new int[]{ 1, 2, 3, 4 } 같은 표현 대신 [1, 2, 3, 4]로 표현 가능.
// 12. React에서는 (정확히는 JSX에서는) 어떤 값을 표현하고 싶을 때 중괄호를 써준다.
//     -> 객체를 표현하고 싶으면 JSON으로 변환해야 함.
//     -> JSON.stringify 함수 사용.
// 13. React에서는 (정확히는 JSX에서는) JavaScript의 기본적인 기능을 활용.
//     -> map을 활용하는 경우가 엄청나게 많다.
// 14. React에서는 처음에 한번만 실행하는 걸 위해 useEffect를 쓸 수 있다.
//     -> useEffect(() => { ... }, []);

import axios from 'axios';

import { useState, useEffect } from 'react';

import './App.css';

function App() {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    const url = 'https://jsonplaceholder.typicode.com/posts';

    axios.get(url).then(response => {
      setPosts(response.data);
    });
  }, []);

  return (
    <div className="App">
      <header className="App-header">
        <p>Hello, world!</p>
        {posts.map(post => {
          return (
            <p key={post.id}>
              {post.title}
            </p>
          )
        })}
      </header>
    </div>
  );
}

export default App;
