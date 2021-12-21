import { render, fireEvent } from '@testing-library/react';

import { Provider } from 'react-redux';

import { setupStore } from '../store';

import Counter from './Counter';

// Just trick 😉
const context = describe;

describe('Counter', () => {
  function renderCounter() {
    const store = setupStore();

    return render((
      <Provider store={store}>
        <Counter />
      </Provider>
    ));
  }

  it('renders counting number', () => {
    const { container } = renderCounter();

    expect(container).toHaveTextContent('0');
  });

  context('when plus button is clicked', () => {
    it('increases counting number', () => {
      const { container, getByText } = renderCounter();

      expect(container).toHaveTextContent('0');

      fireEvent.click(getByText('[+]'));

      expect(container).toHaveTextContent('1');
    });
  });

  context('when minus button is clicked', () => {
    it('decreases counting number', () => {
      const { container, getByText } = renderCounter();

      expect(container).toHaveTextContent('0');

      fireEvent.click(getByText('[-]'));

      expect(container).toHaveTextContent('-1');
    });
  });

  context('when [+2] button is clicked', () => {
    it('increases counting number by 2', () => {
      const { container, getByText } = renderCounter();

      expect(container).toHaveTextContent('0');

      fireEvent.click(getByText('[+2]'));

      expect(container).toHaveTextContent('2');
    });
  });
});
