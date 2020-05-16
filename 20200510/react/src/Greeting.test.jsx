import React from 'react';

import { render } from '@testing-library/react';

import Greeting from './Greeting';

test('Greeting', () => {
  const { container } = render((
    <Greeting />
  ));

  expect(container).toHaveTextContent('Hello, world!');
});
