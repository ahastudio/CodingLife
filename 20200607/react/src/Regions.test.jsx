import React from 'react';

import { render, fireEvent } from '@testing-library/react';

import Regions from './Regions';

test('Regions', () => {
  const regions = [
    { id: 1, name: '서울' },
  ];

  const handleClick = jest.fn();

  const { getByText } = render((
    <Regions
      regions={regions}
      region={null}
      onClick={handleClick}
    />
  ));

  expect(getByText('서울')).not.toBeNull();

  fireEvent.click(getByText('서울'));

  expect(handleClick).toBeCalledWith({ id: 1, name: '서울' });
});
