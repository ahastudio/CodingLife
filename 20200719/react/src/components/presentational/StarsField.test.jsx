import React from 'react';

import { render, fireEvent } from '@testing-library/react';

import StarsField from './StarsField';

describe('StarsField', () => {
  it('renders label and select control', () => {
    const handleChange = jest.fn();

    const { container, queryByLabelText } = render((
      <StarsField
        label="평점"
        name="score"
        onChange={handleChange}
      />
    ));

    expect(container).toHaveTextContent('평점');

    fireEvent.change(queryByLabelText('평점'), {
      target: { value: '3' },
    });

    expect(handleChange).toBeCalledWith({
      name: 'score',
      value: '3',
    });
  });
});
