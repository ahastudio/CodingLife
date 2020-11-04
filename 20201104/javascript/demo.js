function add(x, y) {
  return x + y;
}

function isPalindrome(text) {
  const half = parseInt(text.length / 2, 10);
  return range(half).every((i) => at(text, i) === at(text, -1 - i));
}

function range(size) {
  return Array.from({ length: size }, (_, i) => i);
}

function at(xs, index) {
  return index >= 0 ? xs[index] : xs[xs.length + index];
}

module.exports = {
  add,
  isPalindrome,
  range,
  at,
};
