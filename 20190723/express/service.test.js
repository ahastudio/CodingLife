const service = require('./service');

beforeEach(async () => {
  await service.init();
  await service.clearTasks();
});

test('addTasks', async () => {
  await service.addTask('test1');
  await service.addTask('test2');

  const tasks = await service.getTasks();

  expect(tasks.length).toBe(2);
  expect(tasks[0].title).toBe('test1');
  expect(tasks[1].title).toBe('test2');
});
