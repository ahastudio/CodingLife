import React from 'react';

import { render, fireEvent } from '@testing-library/react';

import Input from './Input';

test('Input', () => {
  const handleChange = jest.fn();
  const handleClick = jest.fn();

  const { getByLabelText, getByDisplayValue, getByText } = render((
    <Input
      value="무언가 하기"
      onChange={handleChange}
      onClick={handleClick}
    />
  ));

  expect(getByLabelText(/할 일/)).not.toBeNull();
  expect(getByDisplayValue(/무언가 하기/)).not.toBeNull();
  expect(getByText(/추가/)).not.toBeNull();

  fireEvent.change(getByLabelText('할 일'), {
    target: { value: '무엇이든 하기' },
  });

  expect(handleChange).toBeCalled();

  fireEvent.click(getByText(/추가/));

  expect(handleClick).toBeCalled();
});
