import React from 'react';

import { render, fireEvent } from '@testing-library/react';

import RegionsMenu from './RegionsMenu';

test('RegionsMenu', () => {
  const regions = [
    { id: 1, name: '서울' },
  ];

  const handleClick = jest.fn();

  const { container, getByText } = render((
    <RegionsMenu
      regions={regions}
      selectedRegion={null}
      onClick={handleClick}
    />
  ));

  expect(container).toHaveTextContent('서울');

  fireEvent.click(getByText('서울'));

  expect(handleClick).toBeCalledWith(regions[0]);
});
