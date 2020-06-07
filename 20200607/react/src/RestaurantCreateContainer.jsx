import React from 'react';

import { useDispatch, useSelector } from 'react-redux';

import RestaurantForm from './RestaurantForm';

import {
  changeRestaurantField,
  addRestaurant,
} from './actions';

export default function RestaurantCreateContainer() {
  const dispatch = useDispatch();

  const { restaurant } = useSelector((state) => ({
    restaurant: state.restaurant,
  }));

  function handleChange({ name, value }) {
    dispatch(changeRestaurantField({ name, value }));
  }

  function handleClick() {
    dispatch(addRestaurant());
  }

  return (
    <RestaurantForm
      restaurant={restaurant}
      onChange={handleChange}
      onClick={handleClick}
    />
  );
}
