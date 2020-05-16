import React from 'react';

import { render, fireEvent } from '@testing-library/react';

import Buttons from './Buttons';

test('Buttons', () => {
  const handleClick = jest.fn();

  const { container, getByText } = render((
    <Buttons onClick={handleClick} />
  ));

  expect(container).toHaveTextContent('1');
  expect(container).toHaveTextContent('2');
  expect(container).toHaveTextContent('3');

  fireEvent.click(getByText('1'));

  expect(handleClick).toBeCalledWith(1);
  expect(handleClick).not.toBeCalledWith(2);
  expect(handleClick).not.toBeCalledWith(3);
});
