import React from 'react';

import { useDispatch, useSelector } from 'react-redux';

import configureStore from 'redux-mock-store';

import { render, fireEvent } from '@testing-library/react';

import App from './App';

import reducer, { initialState } from './reducer';

import {
  addRestaurant,
  changeRestaurantForm,
} from './actions';

jest.mock('react-redux');

const mockStore = configureStore([]);

describe('App', () => {
  test('scenario - add a restaurant', () => {
    const store = mockStore(initialState);

    useDispatch.mockImplementation(() => store.dispatch);
    useSelector.mockImplementation((selector) => selector(store.getState()));

    const { getByText, getByLabelText } = render(<App />);

    expect(getByText(/Restaurants/)).not.toBeNull();

    fireEvent.change(getByLabelText('가게 이름'), {
      target: { value: '김밥제국' },
    });

    fireEvent.change(getByLabelText('분류'), {
      target: { value: '분식' },
    });

    fireEvent.change(getByLabelText('주소'), {
      target: { value: '서울특별시 강남구 역삼동' },
    });

    fireEvent.click(getByText('등록'));

    expect(store.getActions()).toEqual([
      changeRestaurantForm('name', '김밥제국'),
      changeRestaurantForm('category', '분식'),
      changeRestaurantForm('address', '서울특별시 강남구 역삼동'),
      addRestaurant(),
    ]);

    const state = store.getActions().reduce(
      (previousState, action) => reducer(previousState, action),
      store.getState(),
    );

    expect(state.restaurants).toHaveLength(1);

    expect(state.restaurants[0]).toEqual({
      id: 1,
      name: '김밥제국',
      category: '분식',
      address: '서울특별시 강남구 역삼동',
    });
  });
});
