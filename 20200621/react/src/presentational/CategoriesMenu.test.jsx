import React from 'react';

import { render, fireEvent } from '@testing-library/react';

import CategoriesMenu from './CategoriesMenu';

test('CategoriesMenu', () => {
  const categories = [
    { id: 1, name: '한식' },
  ];

  const handleClick = jest.fn();

  const { container, getByText } = render((
    <CategoriesMenu
      categories={categories}
      selectedCategory={null}
      onClick={handleClick}
    />
  ));

  expect(container).toHaveTextContent('한식');

  fireEvent.click(getByText('한식'));

  expect(handleClick).toBeCalledWith(categories[0]);
});
