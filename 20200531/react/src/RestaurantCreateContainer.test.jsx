import React from 'react';

import { render } from '@testing-library/react';

import { useSelector } from 'react-redux';

import RestaurantCreateContainer from './RestaurantCreateContainer';

import fixtures from './fixtures';

jest.mock('react-redux');

test('RestaurantListContainer', () => {
  useSelector.mockImplementation((selector) => selector({
    restaurants: fixtures.restaurants,
    restaurantForm: {},
  }));

  const { getByText } = render((
    <RestaurantCreateContainer />
  ));

  expect(getByText(/등록/)).not.toBeNull();
});
