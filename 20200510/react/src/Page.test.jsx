import React from 'react';

import { render, fireEvent } from '@testing-library/react';

import Page from './Page';

test('Page', () => {
  const handleClick = jest.fn();
  const handleChange = jest.fn();

  const { container, getByText } = render((
    <Page
      count={0}
      onClick={handleClick}
      onChange={handleChange}
    />
  ));

  expect(container).toHaveTextContent('Click me! (0)');

  fireEvent.click(getByText(/Click me/));

  expect(handleClick).toBeCalled();

  fireEvent.click(getByText('1'));

  expect(handleChange).toBeCalledWith(1);
});
