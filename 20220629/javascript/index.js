/* eslint-disable no-console */

// 1. 문자열과 그냥 문자의 구별이 없다.
//    - Java: 문자열("", String), 문자('', char)
//    - JavaScript: 문자열('', String)
// 2. JavaScript는 컴파일이 필요 없다.
//    -> 스크립트 언어의 특징.
// 3. 인터프리터 -> REPL
//    -> node
// 4. 타입
//    - 숫자 (number)
//    - 문자열 (string)
//    - 불리언 (boolean)
//    - undefined -> 특수함
//    - Symbol (지금은 몰라도 됨)
//    - object
//      - {}
//      -> array는 특수한 object ([1, 2, 3])
//      -> 없으면 null
//    -> typeof 연산자로 확인 가능
// 5. 변수
//    - Java: <타입> <변수명>;
//    - JavaScript: let <변수명>;   // -> 바꿀 수 있음. (reassign)
//                  const <변수명>; // -> 바꿀 수 없음. (이걸 권장)
// 6. 함수
//    function <함수명>(<파라미터 목록>) { <여기에 본문> }
// 7. 템플릿 문자열 -> backtick(`) 사용
// 8. 조건문 -> if
// 9. 반복문 -> while, for
// 10. 비교연산자를 쓸 때 == 대신 === 사용.
//     -> 옛날 JavaScript는 ==에서 좀 문제가 있음. (타입 때문)
// 11. 배열에서 많이 쓰는 두 가지: forEach와 map
// 12. Java의 lambda와 유사한 arrow function (=>)

function main() {
  const name = 'Ashal';
  console.log(`Hello, ${name}`);
  console.log(`1 + 2 = ${1 + 2}`);

  const value = 0;
  if (value + 2 > 3) {
    console.log(`${value} + 2가 3보다 크다니!`);
  }

  // for (let number = 1; number < 10; number += 2) {
  //   console.log(number);
  // }

  const numbers = [10, 20, 30, 40, 50];

  // for (let i = 0; i < 8; i += 1) {
  //   console.log(numbers[i % numbers.length]);
  // }

  numbers.forEach((i) => {
    console.log(i);
  });

  const numbers2 = numbers.map((i) => i * 2);
  console.log(numbers2);
}

main();
