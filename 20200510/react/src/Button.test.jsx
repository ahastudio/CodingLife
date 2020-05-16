import React from 'react';

import { render, fireEvent } from '@testing-library/react';

import Button from './Button';

test('Button', () => {
  const handleClick = jest.fn();

  const { container, getByText } = render((
    <Button onClick={handleClick}>
      Click me!
    </Button>
  ));

  expect(container).toHaveTextContent('Click me!');

  fireEvent.click(getByText('Click me!'));

  expect(handleClick).toBeCalled();
});
