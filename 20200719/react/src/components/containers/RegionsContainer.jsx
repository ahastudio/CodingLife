import React, { useCallback } from 'react';

import { useDispatch, useSelector } from 'react-redux';

import Regions from '../presentational/Regions';

import {
  selectRegion,
  loadRestaurants,
} from '../../slice';

import { get } from '../../utils';

export default function RegionsContainer() {
  const dispatch = useDispatch();

  const regions = useSelector(get('regions'));
  const selectedRegion = useSelector(get('selectedRegion'));

  const handleClick = useCallback((regionId) => {
    dispatch(selectRegion(regionId));
    dispatch(loadRestaurants());
  }, [dispatch]);

  return (
    <Regions
      regions={regions}
      selectedRegion={selectedRegion}
      onClick={handleClick}
    />
  );
}
