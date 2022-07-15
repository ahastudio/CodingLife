import { render, screen, fireEvent } from '@testing-library/react';

import TextInput from './TextInput';

import Field from '../models/Field';
import NameField from '../models/NameField';

const context = describe;

describe('TextInput', () => {
  let field: Field;
  const handleChange = jest.fn();

  beforeEach(() => {
    field = new NameField();

    jest.clearAllMocks();
  });

  function renderTextInput() {
    return render((
      <TextInput
        name="name"
        type="text"
        field={field}
        onChange={handleChange}
      />
    ));
  }

  context('when input control is changed', () => {
    const value = 'Text';

    it('calls `onChange`', () => {
      renderTextInput();

      fireEvent.change(screen.getByRole('textbox'), {
        target: { value },
      });

      expect(handleChange).toBeCalledWith(value);
    });
  });
});
