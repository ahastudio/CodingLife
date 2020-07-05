import React from 'react';

import { render, fireEvent } from '@testing-library/react';

import LogoutForm from './LogoutForm';

describe('LogoutForm', () => {
  it('renders “Log out” button', () => {
    const handleClick = jest.fn();

    const { container, getByText } = render((
      <LogoutForm onClick={handleClick} />
    ));

    expect(container).toHaveTextContent('Log out');

    fireEvent.click(getByText('Log out'));

    expect(handleClick).toBeCalled();
  });

  context('when button is not clicked', () => {
    it("doesn't click event handler", () => {
      const handleClick = jest.fn();

      render((
        <LogoutForm onClick={handleClick} />
      ));

      expect(handleClick).not.toBeCalled();
    });
  });

  context('when button is clicked', () => {
    it('calls click event handler', () => {
      const handleClick = jest.fn();

      const { getByText } = render((
        <LogoutForm onClick={handleClick} />
      ));

      fireEvent.click(getByText('Log out'));

      expect(handleClick).toBeCalled();
    });
  });
});
