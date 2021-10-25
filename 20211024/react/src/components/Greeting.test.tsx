import { render } from '@testing-library/react';

import Greeting from './Greeting';

import { useStore } from '../store';

jest.mock('../store', () => ({
  useStore: {
    name: jest.fn(() => ['', jest.fn()]),
  },
}));

describe('Greeting', () => {
  beforeEach(() => {
    jest.clearAllMocks();
  });

  describe('when name is blank', () => {
    beforeEach(() => {
      useStore.name = jest.fn(() => ['']);
    });

    it('renders a greeting message with `world`', () => {
      const { container } = render(<Greeting />);

      expect(container).toHaveTextContent('Hello, world!');
    });
  });

  describe('when name exists', () => {
    beforeEach(() => {
      useStore.name = jest.fn(() => ['John']);
    });

    it('renders a greeting message with the given name', () => {
      const { container } = render(<Greeting />);

      expect(container).toHaveTextContent('Hello, John!');
    });
  });
});
