import { render, screen } from '@testing-library/react';

import Board from './Board';

test('Board', () => {
  render(<Board />);

  screen.getByText(/Board/);
});
