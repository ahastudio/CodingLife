import { render, screen, waitFor } from '@testing-library/react';

import App from './App';

// import useFetchProducts from './hooks/useFetchProducts';

// jest.mock('./hooks/useFetchProducts');

// jest.mock('./hooks/useFetchProducts', () => () => fixtures.products);

test('App', async () => {
  render(<App />);

  await waitFor(() => {
    screen.getByText('Apple');
  });

  // expect(useFetchProducts).toBeCalled();
});
