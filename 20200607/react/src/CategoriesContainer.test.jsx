import React from 'react';

import { useDispatch, useSelector } from 'react-redux';

import { render, fireEvent } from '@testing-library/react';

import CategoriesContainer from './CategoriesContainer';

jest.mock('react-redux');

test('CategoriesContainer', () => {
  const dispatch = jest.fn();

  useDispatch.mockImplementation(() => dispatch);

  useSelector.mockImplementation((selector) => selector({
    categories: [
      { id: 1, name: '한식' },
    ],
    category: null,
    region: null,
  }));

  const { getByText } = render((
    <CategoriesContainer />
  ));

  expect(getByText('한식')).not.toBeNull();

  fireEvent.click(getByText('한식'));

  expect(dispatch).toBeCalled();
});
