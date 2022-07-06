import { render, screen, fireEvent } from '@testing-library/react';

import ToDoApp from './ToDoApp';

test('ToDoApp', () => {
  const title = 'NewTask';

  render(<ToDoApp />);

  fireEvent.change(screen.getByLabelText('Task'), {
    target: { value: title },
  });

  fireEvent.click(screen.getByText('추가'));

  screen.getByText(title);
});
