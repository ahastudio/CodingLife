import React from 'react';

import { render, fireEvent } from '@testing-library/react';

import RestaurantForm from './RestaurantForm';

test('RestaurantForm', () => {
  const restaurantForm = {
    name: '김밥',
  };

  const handleChange = jest.fn();
  const handleSubmit = jest.fn();

  const { getByText, getByDisplayValue } = render((
    <RestaurantForm
      restaurantForm={restaurantForm}
      onChange={handleChange}
      onSubmit={handleSubmit}
    />
  ));

  expect(getByText(/등록/)).not.toBeNull();
  expect(getByDisplayValue(/김밥/)).not.toBeNull();

  fireEvent.click(getByText(/등록/));

  expect(handleSubmit).toBeCalled();
});
