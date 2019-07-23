const { clearTasks, getTasks, addTask } = require('./service');

beforeEach(() => {
  clearTasks();
});

test('addTasks', async () => {
  addTask('test1');
  addTask('test2');

  const tasks = getTasks();

  expect(tasks.length).toBe(2);
  expect(tasks[0].title).toBe('test1');
  expect(tasks[1].title).toBe('test2');
});
