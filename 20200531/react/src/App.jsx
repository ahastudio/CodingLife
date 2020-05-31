// 1. Restaurant List -> State => RestaurantList
// 2. Restaurant Create -> State <- Form => RestaurantForm
// 3. Restaurant Update -> State <- Form => RestaurantForm
// state - restaurants (array), restaurantForm...

import React from 'react';

import RestaurantListContainer from './RestaurantListContainer';
import RestaurantCreateContainer from './RestaurantCreateContainer';

export default function App() {
  return (
    <div>
      <h1>Restaurants</h1>
      <RestaurantListContainer />
      <RestaurantCreateContainer />
    </div>
  );
}
