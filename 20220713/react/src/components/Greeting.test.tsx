import { render, screen } from '@testing-library/react';

import Greeting from './Greeting';

describe('Greeting', () => {
  it('renders greeting message', () => {
    render(<Greeting />);

    screen.getByText(/Hello/);
  });
});
