import { screen, fireEvent } from '@testing-library/react';

import { useState } from 'react';
import { render } from '../../test-helpers';

import TextBox from './TextBox';

const context = describe;

describe('TextBox', () => {
  const handleChange = jest.fn();

  beforeEach(() => {
    jest.clearAllMocks();
  });

  context('when text is changed', () => {
    context('when “onChange” is function', () => {
      const value = 'Old Name';
      const newValue = 'New Name';

      it('calls “onChange” handler', () => {
        render((
          <TextBox
            label="이름"
            value={value}
            onChange={handleChange}
          />
        ));

        fireEvent.change(screen.getByLabelText('이름'), {
          target: { value: newValue },
        });

        expect(handleChange).toBeCalledWith(newValue);
      });
    });

    context('when “onChange” is undefined', () => {
      const value = 'Old Name';
      const newValue = 'New Name';

      it("doesn't crash", () => {
        render((
          <TextBox
            label="이름"
            value={value}
          />
        ));

        fireEvent.change(screen.getByLabelText('이름'), {
          target: { value: newValue },
        });
      });
    });
  });
});
