import React from 'react';

import { render, fireEvent } from '@testing-library/react';

import Categories from './Categories';

test('Categories', () => {
  const categories = [
    { id: 1, name: '한식' },
  ];

  const handleClick = jest.fn();

  const { getByText } = render((
    <Categories
      categories={categories}
      category={null}
      onClick={handleClick}
    />
  ));

  expect(getByText('한식')).not.toBeNull();

  fireEvent.click(getByText('한식'));

  expect(handleClick).toBeCalledWith({
    id: 1,
    name: '한식',
  });
});
