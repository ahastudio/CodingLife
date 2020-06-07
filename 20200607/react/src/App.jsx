import React, { useEffect } from 'react';

import { useDispatch } from 'react-redux';

import CategoriesContainer from './CategoriesContainer';
import RegionsContainer from './RegionsContainer';
import RestaurantsContainer from './RestaurantsContainer';
import RestaurantCreateContainer from './RestaurantCreateContainer';

import {
  loadRegions,
  loadCategories,
} from './actions';

export default function App() {
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(loadCategories());
    dispatch(loadRegions());
  }, []);

  return (
    <div>
      <h1>Restaurants</h1>
      <CategoriesContainer />
      <RegionsContainer />
      <RestaurantsContainer />
      <RestaurantCreateContainer />
    </div>
  );
}
