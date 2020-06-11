import React from 'react';

import { render, fireEvent } from '@testing-library/react';

import { useDispatch, useSelector } from 'react-redux';

import ListContainer from './ListContainer';

jest.mock('react-redux');

describe('ListContainer', () => {
  const dispatch = jest.fn();

  beforeEach(() => {
    dispatch.mockClear();

    useDispatch.mockImplementation(() => dispatch);

    useSelector.mockImplementation((selector) => selector({
      tasks: [
        { id: 1, title: '아무 것도 하지 않기 #1' },
        { id: 2, title: '아무 것도 하지 않기 #2' },
      ],
    }));
  });

  it('renders tasks', () => {
    const { queryByText } = render((
      <ListContainer />
    ));

    expect(queryByText(/아무 것도 하지 않기 #1/)).not.toBeNull();
    expect(queryByText(/아무 것도 하지 않기 #1/)).not.toBeNull();
  });

  it('renders “완료” button to delete a task', () => {
    const { getAllByText } = render((
      <ListContainer />
    ));

    const buttons = getAllByText('완료');

    fireEvent.click(buttons[0]);

    expect(dispatch).toBeCalled();
  });
});
