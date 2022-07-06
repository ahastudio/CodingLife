import { render, screen } from '@testing-library/react';

import ToDoList from './ToDoList';

test('ToDoList', () => {
  const tasks = [
    { id: 1, title: 'Do nothing' },
    { id: 2, title: 'Play' },
  ];

  render(<ToDoList tasks={tasks} />);

  screen.getByText('Do nothing');
  screen.getByText('Play');
});
