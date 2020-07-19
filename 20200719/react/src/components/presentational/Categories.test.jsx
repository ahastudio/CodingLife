import React from 'react';

import { render, fireEvent } from '@testing-library/react';

import Categories from './Categories';

const categories = [
  { id: 1, name: '한식' },
  { id: 2, name: '중식' },
  { id: 3, name: '일식' },
];

test('Categories', () => {
  const handleClick = jest.fn();

  const { container, getByText } = render((
    <Categories
      categories={categories}
      selectedCategory={categories[1]}
      onClick={handleClick}
    />
  ));

  expect(container).toHaveTextContent('한식');
  expect(container).toHaveTextContent('중식(V)');
  expect(container).toHaveTextContent('일식');

  fireEvent.click(getByText('한식'));

  expect(handleClick).toBeCalled();
});
