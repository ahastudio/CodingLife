import { render, waitFor } from '@testing-library/react';

import { Provider } from 'react-redux';

import { setupStore } from './store';

import App from './App';

describe('App', () => {
  function renderApp() {
    const store = setupStore();

    return render((
      <Provider store={store}>
        <App />
      </Provider>
    ));
  }

  it('renders greeting message', () => {
    const { container } = renderApp();

    expect(container).toHaveTextContent('Hello, world!');
  });

  it('renders <Counter> component', () => {
    const { container } = renderApp();

    expect(container).toHaveTextContent('0 [+] [-]');
  });

  it('renders <Restaurants> component', async () => {
    const { container } = renderApp();

    expect(container).toHaveTextContent('Loading...');

    await waitFor(() => {
      expect(container).toHaveTextContent('양천주가');
    });
  });
});
