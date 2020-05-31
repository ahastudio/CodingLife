import React from 'react';

import { render, fireEvent } from '@testing-library/react';

import RestaurantForm from './RestaurantForm';

describe('RestaurantForm', () => {
  const restaurantForm = {
    name: '김밥',
    category: '',
    address: '',
  };

  it('renders submit button', () => {
    const handleChange = jest.fn();
    const handleSubmit = jest.fn();

    const { getByText } = render((
      <RestaurantForm
        restaurantForm={restaurantForm}
        onChange={handleChange}
        onSubmit={handleSubmit}
      />
    ));

    expect(getByText(/등록/)).not.toBeNull();

    fireEvent.click(getByText(/등록/));

    expect(handleSubmit).toBeCalled();
  });

  it('renders input controls', () => {
    const handleChange = jest.fn();
    const handleSubmit = jest.fn();

    const { getByDisplayValue, getByLabelText } = render((
      <RestaurantForm
        restaurantForm={restaurantForm}
        onChange={handleChange}
        onSubmit={handleSubmit}
      />
    ));

    expect(getByDisplayValue(/김밥/)).not.toBeNull();

    fireEvent.change(getByLabelText('가게 이름'), {
      target: { value: '김밥왕국' },
    });

    expect(handleChange).toBeCalledWith('name', '김밥왕국');
  });
});
