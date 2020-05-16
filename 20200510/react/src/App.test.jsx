import React from 'react';

import { render, fireEvent } from '@testing-library/react';

import App from './App';

test('App', () => {
  const { container, getByText } = render((
    <App />
  ));

  expect(container).toHaveTextContent('Hello, world!');

  fireEvent.click(getByText(/Click me/));

  expect(container).toHaveTextContent('Click me! (1)');

  fireEvent.click(getByText(/Click me/));

  expect(container).toHaveTextContent('Click me! (2)');

  fireEvent.click(getByText('1'));

  expect(container).toHaveTextContent('Click me! (1)');
});
