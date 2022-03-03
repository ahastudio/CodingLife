import { render, screen } from '@testing-library/react';

import TaskForm from './TaskForm';

describe('TaskForm', () => {
  it('renders form', () => {
    render((
      <TaskForm />
    ));

    screen.getByRole('form');
  });
});
