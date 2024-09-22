function str2sec(text) {
  const [mininutes, seconds] = text.split(':').map((i) => parseInt(i, 10));
  return mininutes * 60 + seconds;
}

function sec2str(timeInSeconds) {
  const minutes = Math.floor(timeInSeconds / 60);
  const seconds = timeInSeconds % 60;
  return [minutes, seconds].map((i) => i.toString().padStart(2, '0')).join(':');
}

function solution(video_len, pos, op_start, op_end, commands) {
  const videoEnd = str2sec(video_len);
  const current = str2sec(pos);
  const openingStart = str2sec(op_start);
  const openingEnd = str2sec(op_end);
  const fs = {
    next: (x) => Math.min(x + 10, videoEnd),
    prev: (x) => Math.max(x - 10, 0),
  };
  const skipOpening = (x) => (
    x >= openingStart && x < openingEnd ? openingEnd : x
  );
  return sec2str(
    commands.reduce(
      (acc, command) => skipOpening(fs[command](acc)),
      skipOpening(current)
    )
  );
}

// Test Code ------------------------------------------------------------------

// const test = () => {};

test('solution', () => {
  expect(
    solution('34:33', '13:00', '00:55', '02:55', ['next', 'prev'])
  ).toBe('13:00');

  expect(
    solution('10:55', '00:05', '00:15', '06:55', ['prev', 'next', 'next'])
  ).toBe('06:55');

  expect(
    solution('07:22', '04:05', '00:15', '04:07', ['next'])
  ).toBe('04:17');
});

test('str2sec', () => {
  expect(str2sec('00:00')).toBe(0);
  expect(str2sec('00:32')).toBe(32);
  expect(str2sec('01:32')).toBe(60 + 32);
  expect(str2sec('31:32')).toBe(31 * 60 + 32);
});

test('sec2str', () => {
  expect(sec2str(0)).toBe('00:00');
  expect(sec2str(32)).toBe('00:32');
  expect(sec2str(60)).toBe('01:00');
  expect(sec2str(60 + 3)).toBe('01:03');
  expect(sec2str(60 + 32)).toBe('01:32');
  expect(sec2str(31 * 60 + 32)).toBe('31:32');
});
