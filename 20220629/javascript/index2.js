/* eslint-disable no-console */

// 1. 객체 -> key-value 쌍의 모음.
//    Car 클래스 -> car 인스턴스 (run이란 메서드)
// 2. 함수도 일등급 타입
// 3. 메서드(객체와 연결된 함수, this 같은 게 잘 쓰임)로 쓸 때는 주의가 필요함.
// 4. JavaScript는 Prototype-based OOPL
//    -> Java는 Class-based OOPL
// 5. JavaScript를 함수형 프로그래밍 언어처럼 사용하려고 함. (요즘 추세)
//    -> 객체는 잘 정의된 데이터 집합.
// 6. Spread Syntax => ...
// 7. undefined => 정의 되지 않음, 초기화되지 않음
// 8. Destructuring
// 9. 재귀 호출 -> 점화식(반복되는 동형의 무언가), 종료 조건
// 10. RORO pattern -> object와 destructuring 활용

function carExample() {
  const car = {
    name: '자동차',
    run() {
      console.log('부릉부릉!');
      console.log(`나는 ${this.name}`);
    },
  };

  car.run();
}

function isBlock({
  size, matrix, x, y,
}) {
  return x < 0 || x >= size || y < 0 || y >= size || matrix[y][x] > 0;
}

function step({
  size, matrix, position, direction, number,
}) {
  // 종료 조건
  if (number > size * size) {
    return;
  }

  const { x, y } = position;

  // eslint-disable-next-line no-param-reassign
  matrix[y][x] = number;

  const blocked = isBlock({
    size, matrix, x: x + direction.x, y: y + direction.y,
  });

  const newDirection = !blocked ? direction : {
    x: -direction.y,
    y: direction.x,
  };

  // 재귀 호출
  step({
    size,
    matrix,
    position: {
      x: x + newDirection.x,
      y: y + newDirection.y,
    },
    direction: newDirection,
    number: number + 1,
  });
}

function spiralMatrix() {
  const size = 10;

  const matrix = [...Array(size)].map(() => (
    Array(size).fill(0)
  ));

  const position = { x: 0, y: 0 };
  const direction = { x: 1, y: 0 };

  step({
    size, matrix, position, direction, number: 1,
  });

  matrix.forEach((row) => {
    console.log(row.join('\t'));
  });
}

carExample();
console.log();
spiralMatrix();

// Sprial Matrix 풀이 코드:
// https://github.com/ahastudio/CodingLife/blob/main/20181025/javascript/spiral.js
