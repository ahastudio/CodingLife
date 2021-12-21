import { render } from '@testing-library/react';

// Just trick 😉
const context = describe;

let queryResult = {
  data: undefined,
  error: undefined,
  isLoading: undefined,
};

jest.doMock('../services/restaurant', () => ({
  useGetRestaurantQuery: jest.fn(() => queryResult),
}));

const Restaurants = require('./Restaurants').default;

beforeEach(() => {
  jest.clearAllMocks();
});

describe('Restaurants', () => {
  context('when loading is complete', () => {
    beforeEach(() => {
      queryResult = {
        data: [
          {
            id: 1, categoryId: 1, name: '양천주가', address: '서울 강남구',
          },
        ],
      };
    });

    it('renders a list of restaurant', () => {
      const { container } = render(<Restaurants />);

      expect(container).toHaveTextContent('양천주가');
    });
  });

  context('when loading is not complete', () => {
    beforeEach(() => {
      queryResult = {
        isLoading: true,
      };
    });

    it('renders loading message', () => {
      const { container } = render(<Restaurants />);

      expect(container).toHaveTextContent('Loading...');
    });
  });

  context('when error occurs', () => {
    beforeEach(() => {
      queryResult = {
        error: 'I have an error!',
      };
    });

    it('renders error message', () => {
      const { container } = render(<Restaurants />);

      expect(container).toHaveTextContent('error');
    });
  });
});
