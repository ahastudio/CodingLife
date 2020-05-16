import React from 'react';

import { render, fireEvent } from '@testing-library/react';

import Counter from './Counter';

test('Counter', () => {
  const handleClick = jest.fn();

  const { container, getByText } = render((
    <Counter
      count={0}
      onClick={handleClick}
    />
  ));

  expect(container).toHaveTextContent('Click me! (0)');

  fireEvent.click(getByText(/Click me/));

  expect(handleClick).toBeCalled();
});
