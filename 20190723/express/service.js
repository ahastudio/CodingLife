const Storage = require('node-storage');

const generateId = () => process.hrtime.bigint().toString();

const dbName = process.env.NODE_ENV === 'test'
  ? `/tmp/data/tasks-${generateId()}.json`
  : './data/tasks.json';

const store = new Storage(dbName);

const getTasks = () => store.get('tasks') || [];

const addTask = (title) => {
  const tasks = getTasks();
  const id = generateId();
  store.put('tasks', [
    ...tasks,
    { id, title },
  ]);
};

const clearTasks = async () => {
  store.put('tasks', []);
};

module.exports = {
  clearTasks,
  getTasks,
  addTask,
};
