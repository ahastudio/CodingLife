import React from 'react';

import { useSelector } from 'react-redux';

import RestaurantList from './RestaurantList';

export default function RestaurantListContainer() {
  const { restaurants } = useSelector((state) => ({
    restaurants: state.restaurants,
  }));

  return (
    <RestaurantList restaurants={restaurants} />
  );
}
