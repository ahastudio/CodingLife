import React from 'react';

import { render } from '@testing-library/react';

import RestaurantList from './RestaurantList';

import fixtures from '../fixtures';

test('RestaurantList', () => {
  const { restaurants } = fixtures;

  const { getByText } = render((
    <RestaurantList restaurants={restaurants} />
  ));

  expect(getByText(/마법사주방/)).not.toBeNull();
});
