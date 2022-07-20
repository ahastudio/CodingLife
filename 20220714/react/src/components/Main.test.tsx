import { render, screen } from '@testing-library/react';

import Main from './Main';

describe('Greeting', () => {
  it('renders greeting message', () => {
    render(<Main />);

    screen.getByText(/Hello, world/);
  });
});
