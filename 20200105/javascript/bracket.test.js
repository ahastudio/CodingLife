const solution = p => {
  if (!p) {
    return '';
  }
  const { u, v } = uv(p);
  return u[0] === '(' ? u + solution(v) : `(${solution(v)})${fixU(u)}`;
};

const uv = p => [...p].reduce(({ u, v, c }, x) => (
  c !== 0
  ? { u: u + x, v, c: (c || 0) + { '(': +1, ')': -1 }[x] }
  : { u, v: v + x, c }
), { u: '', v: '', c: null });

const r = x => ({ '(': ')', ')': '(' })[x];
const fixU = u => [...u.slice(1, u.length - 1)].map(r).join('');

// test --------------

test('sample', () => {
  expect(solution('()')).toBe('()');
  expect(solution('(()())()')).toBe('(()())()');
  expect(solution(')(')).toBe('()');
});

test('uv', () => {
  expect(uv('()').u).toMatch('()');
  expect(uv('()').v).toMatch('');

  expect(uv(')(').u).toMatch(')(');
  expect(uv(')(').v).toMatch('');

  expect(uv('()(').u).toMatch('()');
  expect(uv('()(').v).toMatch('(');
});

test('fixU', () => {
  expect(fixU('()')).toMatch('');
  expect(fixU('))((')).toMatch('()');
});
