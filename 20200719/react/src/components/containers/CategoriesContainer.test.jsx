import React from 'react';

import { render, fireEvent } from '@testing-library/react';

import { useDispatch, useSelector } from 'react-redux';

import CategoriesContainer from './CategoriesContainer';

test('CategoriesContainer', () => {
  const dispatch = jest.fn();

  useDispatch.mockImplementation(() => dispatch);

  useSelector.mockImplementation((selector) => selector({
    categories: [
      { id: 1, name: '한식' },
      { id: 2, name: '중식' },
      { id: 3, name: '일식' },
    ],
  }));

  const { container, getByText } = render((
    <CategoriesContainer />
  ));

  expect(container).toHaveTextContent('한식');

  fireEvent.click(getByText('한식'));

  expect(dispatch).toBeCalled();
});
