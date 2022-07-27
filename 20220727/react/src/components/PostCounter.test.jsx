import { render, screen } from '@testing-library/react';

import PostCounter from './PostCounter';

test('PostCounter', () => {
  render(<PostCounter />);

  screen.getByText(/Count: /);
});
