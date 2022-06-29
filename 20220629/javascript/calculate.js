// 1. export -> import
// 2. SWC -> @swc/jest
// 3. Object의 property 접근 방식 두 가지: .어쩌고 / ['어쩌고']
// 4. Object를 이용해서 if문을 대체할 수 있다.

export function plus(x, y) {
  return x + y;
}

export function minus(x, y) {
  return x - y;
}

const operationFunctions = {
  '+': plus,
  '-': minus,
};

export default function calcaulte(operation, x, y) {
  const f = operationFunctions[operation];
  return f(x, y);
}
