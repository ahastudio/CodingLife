import { fireEvent, render, screen } from '@testing-library/react';

import ToDoInput from './ToDoInput';

test('ToDoInput', () => {
  const title = 'Test';

  const addTask = jest.fn();

  render((
    <ToDoInput addTask={addTask} />
  ));

  fireEvent.change(screen.getByLabelText('Task'), {
    target: { value: title },
  });

  fireEvent.click(screen.getByText('추가'));

  expect(addTask).toBeCalledWith(title);
});
