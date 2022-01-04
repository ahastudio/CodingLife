import { render } from '@testing-library/react';

import App from './App';

jest.mock('@apollo/client', () => ({
  gql: jest.fn((x) => x),
  useApolloClient: jest.fn(),
  useQuery: jest.fn(() => ({
    loading: false,
    error: undefined,
    data: {
      allFilms: {
        films: [
          // TODO: if you want, fill this place.
        ],
      },
    },
  })),
}));

describe('App', () => {
  it('renders greeting message', () => {
    const { container } = render(<App />);

    expect(container).toHaveTextContent('Hello, world!');
  });
});
