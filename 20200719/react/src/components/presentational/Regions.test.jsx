import React from 'react';

import { render, fireEvent } from '@testing-library/react';

import Regions from './Regions';

const regions = [
  { id: 1, name: '서울' },
  { id: 2, name: '대전' },
  { id: 3, name: '대구' },
  { id: 4, name: '부산' },
];

test('Regions', () => {
  const handleClick = jest.fn();

  const { container, getByText } = render((
    <Regions
      regions={regions}
      selectedRegion={regions[1]}
      onClick={handleClick}
    />
  ));

  expect(container).toHaveTextContent('서울');
  expect(container).toHaveTextContent('대전(V)');
  expect(container).toHaveTextContent('대구');
  expect(container).toHaveTextContent('부산');

  fireEvent.click(getByText('서울'));

  expect(handleClick).toBeCalled();
});
