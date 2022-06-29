/* eslint-disable no-console */

// 1. 비동기 처리 (통신, I/O)
//    Promise -> async function과 await를 써서 쉽게 사용할 수 있다.
// 2. import할 때 from 부분에 그냥 쓰면 설치한 라이브러리(package) 사용,
//                         경로(./, ../, ./src)를 쓰면 경로에 맞는 코드 사용.

import axios from 'axios';

export async function fetchExampleText() {
  const { data } = await axios.get('http://www.example.com/');

  return data;
}

export function upper(text) {
  return text.toUpperCase();
}

export function print(text) {
  console.log(text);
}
