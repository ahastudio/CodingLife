import React from 'react';

import { useSelector } from 'react-redux';

import Restaurants from '../presentational/Restaurants';

import { get } from '../../utils';

export default function RestaurantsContainer({ onClickRestaurant }) {
  const restaurants = useSelector(get('restaurants'));

  if (!restaurants.length) {
    return null;
  }

  return (
    <Restaurants
      restaurants={restaurants}
      onClick={onClickRestaurant}
    />
  );
}
