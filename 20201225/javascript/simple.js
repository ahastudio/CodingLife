const { log: print } = console;

function add(x, y) {
  let result = 0;
  let delta = x > 0 ? 1 : -1;
  for (let i = 0; i < Math.abs(x); i += 1) {
    result += delta;
  }
  delta = y > 0 ? 1 : -1;
  for (let i = 0; i < Math.abs(y); i += 1) {
    result += delta;
  }
  return result;
}

function assertEqual(expected, actual) {
  if (expected !== actual) {
    print('Error!');
  }
}

assertEqual(7, add(3, 4));

// print(add(3, 4)); // 7
// print(add(3, -4)); // -1
// print(add(-3, -4)); // -7
// print(add(-3, 4)); // 1

// Given - When - Then

// 1. 이게 있으면 좋을텐데... => 감사합니다.
// 2. 표현력 => 이걸 어떻게 더 잘 알 수 있지?
// 3. 이상한데? -> ...테스트 코드를 리팩터링하기...
// 테스트 코드도 제품 코드다. -> 유지보수해야 한다.

module.exports = {
  add,
};
