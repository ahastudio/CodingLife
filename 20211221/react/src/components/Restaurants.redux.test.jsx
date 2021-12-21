import { render, waitFor } from '@testing-library/react';

import { Provider } from 'react-redux';

import { setError, clearError } from '../mocks/handlers';

import { setupStore } from '../store';

import Restaurants from './Restaurants';

// Just trick 😉
const context = describe;

describe('Restaurants', () => {
  function renderRestaurants() {
    const store = setupStore();

    return render((
      <Provider store={store}>
        <Restaurants />
      </Provider>
    ));
  }

  context('when loading is complete', () => {
    it('renders a list of restaurant', async () => {
      const { container } = renderRestaurants();

      expect(container).toHaveTextContent('Loading...');

      await waitFor(() => {
        expect(container).toHaveTextContent('양천주가');
      });
    });
  });

  context('when error occurs', () => {
    beforeEach(() => setError());
    afterEach(() => clearError());

    it('renders error message', async () => {
      const { container } = renderRestaurants();

      expect(container).toHaveTextContent('Loading...');

      await waitFor(() => {
        expect(container).toHaveTextContent('error');
      });
    });
  });
});
