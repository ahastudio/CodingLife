import React from 'react';

import { render } from '@testing-library/react';

import { useSelector } from 'react-redux';

import App from './App';

import fixtures from './fixtures';

jest.mock('react-redux');

test('App', () => {
  useSelector.mockImplementation((selector) => selector({
    restaurants: fixtures.restaurants,
    restaurantForm: {},
  }));

  const { getByText } = render((
    <App />
  ));

  expect(getByText(/Restaurants/)).not.toBeNull();
});
