import { render, screen } from '@testing-library/react';

import App from './App';

test('App', () => {
  render(<App />);

  screen.getByText(/Hello/);
});
