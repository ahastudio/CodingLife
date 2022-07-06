import { render, screen, fireEvent } from '@testing-library/react';

import InputName from './InputName';

test('InputName', () => {
  const name = 'world';

  const handleChange = jest.fn();

  render((
    <InputName
      name={name}
      onChange={handleChange}
    />
  ));

  screen.getByLabelText('Name');

  screen.getByDisplayValue(name);

  fireEvent.change(screen.getByLabelText('Name'), {
    target: { value: 'Ashal' },
  });

  expect(handleChange).toBeCalled();
});
