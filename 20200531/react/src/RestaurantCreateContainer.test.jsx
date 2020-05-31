import React from 'react';

import { render, fireEvent } from '@testing-library/react';

import { useDispatch, useSelector } from 'react-redux';

import RestaurantCreateContainer from './RestaurantCreateContainer';

import {
  changeRestaurantForm,
  addRestaurant,
} from './actions';

import fixtures from './fixtures';

jest.mock('react-redux');

test('RestaurantListContainer', () => {
  const dispatch = jest.fn();

  useDispatch.mockImplementation(() => dispatch);

  useSelector.mockImplementation((selector) => selector({
    restaurants: fixtures.restaurants,
    restaurantForm: {},
  }));

  const { getByText, getByLabelText } = render((
    <RestaurantCreateContainer />
  ));

  fireEvent.change(getByLabelText('가게 이름'), {
    target: { value: '김밥제국' },
  });

  expect(dispatch).toBeCalledWith(changeRestaurantForm('name', '김밥제국'));

  fireEvent.change(getByLabelText('분류'), {
    target: { value: '분식' },
  });

  expect(dispatch).toBeCalledWith(changeRestaurantForm('category', '분식'));

  fireEvent.change(getByLabelText('주소'), {
    target: { value: '서울특별시 강남구 역삼동' },
  });

  expect(dispatch).toBeCalledWith((
    changeRestaurantForm('address', '서울특별시 강남구 역삼동')
  ));

  fireEvent.click(getByText('등록'));

  expect(dispatch).toBeCalledWith(addRestaurant());
});
