import React from 'react';

import { render } from '@testing-library/react';

import { useSelector } from 'react-redux';

import RestaurantListContainer from './RestaurantListContainer';

import fixtures from './fixtures';

jest.mock('react-redux');

test('RestaurantListContainer', () => {
  useSelector.mockImplementation((selector) => selector({
    restaurants: fixtures.restaurants,
  }));

  const { getByText } = render((
    <RestaurantListContainer />
  ));

  expect(getByText(/마법사주방/)).not.toBeNull();
});
