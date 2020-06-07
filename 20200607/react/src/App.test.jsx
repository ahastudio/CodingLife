import React from 'react';

import { useDispatch, useSelector } from 'react-redux';

import { render } from '@testing-library/react';

import App from './App';

jest.mock('react-redux');
jest.mock('./services/api');

test('App', () => {
  const dispatch = jest.fn();

  useDispatch.mockImplementation(() => dispatch);

  useSelector.mockImplementation((selector) => selector({
    restaurants: [],
    restaurant: {},
    categories: [],
    regions: [],
  }));

  const { queryByText } = render((
    <App />
  ));

  expect(dispatch).toBeCalledTimes(2);

  expect(queryByText(/김밥제국/)).toBeNull();
});
