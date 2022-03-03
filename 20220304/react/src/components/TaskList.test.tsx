import { render, act, screen } from '@testing-library/react';

import { RecoilRoot } from 'recoil';

import { todoService } from '../contexts/TodoServiceContext';

import TaskList from './TaskList';

const context = describe;

describe('TaskList', () => {
  function renderScreen() {
    render((
      <RecoilRoot>
        <TaskList />
      </RecoilRoot>
    ));
  }

  context('with tasks', () => {
    it('renders “No task” message', () => {
      renderScreen();

      act(() => {
        todoService.clear();
        todoService.addTask('Run');
      });

      screen.getByText(/No task/);
    });
  });

  context('without tasks', () => {
    it('renders “No task” message', () => {
      renderScreen();

      act(() => {
        todoService.clear();
      });

      screen.getByText(/No task/);
    });
  });
});
