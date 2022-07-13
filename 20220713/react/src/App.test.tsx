import { render } from '@testing-library/react';

import { StoreProvider } from './wrappers';

import App from './App';

describe('App', () => {
  it('renders without crashing', () => {
    render(<App />, { wrapper: StoreProvider });
  });
});
