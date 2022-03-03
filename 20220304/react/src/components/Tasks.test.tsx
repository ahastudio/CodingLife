import { render, act, screen } from '@testing-library/react';

import { RecoilRoot } from 'recoil';

import { todoService } from '../contexts/TodoServiceContext';

import Tasks from './Tasks';

const context = describe;

describe('Tasks', () => {
  const toggle = jest.fn();

  function renderScreen() {
    render((
      <RecoilRoot>
        <Tasks toggle={toggle} />
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
