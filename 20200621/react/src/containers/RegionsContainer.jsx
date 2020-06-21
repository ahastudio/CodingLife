import React from 'react';

import { useDispatch, useSelector } from 'react-redux';

import RegionsMenu from '../presentational/RegionsMenu';

import {
  selectRegion,
  loadRestaurants,
} from '../actions';

import { get } from '../utils';

export default function RegionsContainer() {
  const dispatch = useDispatch();

  const regions = useSelector(get('regions'));
  const selectedRegion = useSelector(get('selectedRegion'));

  function handleClick(region) {
    dispatch(selectRegion(region.id));
    dispatch(loadRestaurants());
  }

  return (
    <RegionsMenu
      regions={regions}
      selectedRegion={selectedRegion}
      onClick={handleClick}
    />
  );
}
