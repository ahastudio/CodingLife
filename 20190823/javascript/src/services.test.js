jest.mock('./utils/counter');

const { Counter } = require('./utils/counter');

let count = 100;

Counter.mockImplementation(() =>
  () => count
);

const { addTask, toggleTask } = require('./services');

test('addTask', () => {
  const state = addTask({ tasks: [] }, 'Hello');

  expect(state.tasks).toEqual([
    { id: 100, title: 'Hello', completed: false },
  ]);
});

test('toggleTask', () => {
  const state = toggleTask({
    tasks: [
      { id: 100, title: 'Hello', completed: false },
      { id: 200, title: 'Hi', completed: false },
    ]
  }, 100);

  expect(state.tasks).toEqual([
    { id: 100, title: 'Hello', completed: true },
    { id: 200, title: 'Hi', completed: false },
  ]);
});
