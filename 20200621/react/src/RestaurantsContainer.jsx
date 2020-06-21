import React from 'react';

import { useSelector } from 'react-redux';

import { get } from './utils';

export default function RestaurantsContainer({ onClickRestaurant }) {
  const restaurants = useSelector(get('restaurants'));

  function handleClick(restaurant) {
    return (event) => {
      event.preventDefault();
      onClickRestaurant(restaurant);
    };
  }

  return (
    <ul>
      {restaurants.map((restaurant) => (
        <li key={restaurant.id}>
          <a
            href={`/restaurants/${restaurant.id}`}
            onClick={handleClick(restaurant)}
          >
            {restaurant.name}
          </a>
        </li>
      ))}
    </ul>
  );
}
