import React from 'react';

import { render, fireEvent } from '@testing-library/react';

import Restaurants from './Restaurants';

test('Restaurants', () => {
  const restaurants = [
    { id: 1, name: '마법사주방' },
    { id: 2, name: '김밥왕국' },
  ];

  const handleClick = jest.fn();

  const { container, getByText } = render((
    <Restaurants
      restaurants={restaurants}
      onClick={handleClick}
    />
  ));

  expect(container).toHaveTextContent('마법사주방');
  expect(container).toHaveTextContent('김밥왕국');

  fireEvent.click(getByText('마법사주방'));

  expect(handleClick).toBeCalledWith(restaurants[0]);
});
