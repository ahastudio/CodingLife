import React, { useEffect, useCallback } from 'react';

import { useHistory } from 'react-router-dom';

import { useDispatch } from 'react-redux';

import RegionsContainer from '../components/containers/RegionsContainer';
import CategoriesContainer from '../components/containers/CategoriesContainer';
import RestaurantsContainer from '../components/containers/RestaurantsContainer';

import {
  loadInitialData,
} from '../slice';


export default function RestaurantsPage() {
  const history = useHistory();

  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(loadInitialData());
  });

  const handleClickRestaurant = useCallback((restaurant) => {
    const url = `/restaurants/${restaurant.id}`;
    history.push(url);
  }, [history]);

  return (
    <div>
      <RegionsContainer />
      <CategoriesContainer />
      <RestaurantsContainer onClickRestaurant={handleClickRestaurant} />
    </div>
  );
}
