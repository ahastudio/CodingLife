import { render, screen } from '@testing-library/react';

import Greeting from './Greeting';

test('Greeting', () => {
  render(<Greeting name="world" />);

  screen.getByText('Hello, world!');
});
