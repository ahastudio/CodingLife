import React from 'react';

import { useDispatch, useSelector } from 'react-redux';

import Regions from './Regions';

import {
  selectRegion,
  loadRestaurants,
} from './actions';

export default function RegionsContainer() {
  const dispatch = useDispatch();

  const {
    regions, category, selectedRegion,
  } = useSelector((state) => ({
    regions: state.regions,
    category: state.category,
    selectedRegion: state.region,
  }));

  function handleClick(region) {
    dispatch(selectRegion(region));
    dispatch(loadRestaurants({ category, region }));
  }

  return (
    <Regions
      regions={regions}
      region={selectedRegion}
      onClick={handleClick}
    />
  );
}
