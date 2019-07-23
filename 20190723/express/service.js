const storage = require('node-persist');

const init = async () => {
  await storage.init();
};

const getTasks = async () => await storage.getItem('tasks') || [];

const addTask = async (title) => {
  const tasks = await getTasks();
  const id = process.hrtime.bigint().toString();
  await storage.setItem('tasks', [
    ...tasks,
    { id, title },
  ]);
};

const clearTasks = async () => {
  await storage.setItem('tasks', []);
};

module.exports = {
  init,
  clearTasks,
  getTasks,
  addTask,
};
