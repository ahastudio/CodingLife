const { status } = require('server/reply');
const {
  get, post, put, del,
} = require('server/router');

const state = {
  tasks: [],
};

function generateId() {
  const { tasks } = state;
  const [task] = tasks;
  return (task?.id || 0) + 1;
}

module.exports = [
  get('/', () => 'Hello, world!'),

  get('/tasks', () => state.tasks),

  post('/tasks', (ctx) => {
    const { body } = ctx.body;
    if (!body?.trim()) {
      return status(404);
    }
    state.tasks = [
      { id: generateId(), body, status: 'todo' },
      ...state.tasks,
    ];
    return 'ok';
  }),

  put('/tasks/:id', (ctx) => {
    const id = parseInt(ctx.params.id, 10);
    const { status: newStatus } = ctx.body;
    state.tasks = state.tasks.map((task) => (
      task.id === id ? { ...task, status: newStatus } : task
    ));
    return 'ok';
  }),

  del('/tasks/:id', (ctx) => {
    const id = parseInt(ctx.params.id, 10);
    state.tasks = state.tasks.filter((task) => task.id !== id);
    return 'ok';
  }),
];
