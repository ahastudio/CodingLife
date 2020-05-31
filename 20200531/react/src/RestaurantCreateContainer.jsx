import React from 'react';

import { useDispatch, useSelector } from 'react-redux';

import RestaurantForm from './components/RestaurantForm';

import {
  addRestaurant,
  changeRestaurantForm,
} from './actions';

export default function RestaurantCreateContainer() {
  const dispatch = useDispatch();

  const { restaurantForm } = useSelector((state) => ({
    restaurantForm: state.restaurantForm,
  }));

  function handleChange(fieldName, value) {
    dispatch(changeRestaurantForm(fieldName, value));
  }

  function handleSubmit() {
    dispatch(addRestaurant());
  }

  return (
    <RestaurantForm
      restaurantForm={restaurantForm}
      onChange={handleChange}
      onSubmit={handleSubmit}
    />
  );
}
