import { render } from '@testing-library/react';

import App from './App';

jest.mock('./components/Counter', () => jest.fn(() => (
  <div data-testid="Counter" />
)));

jest.mock('./components/Restaurants', () => jest.fn(() => (
  <div data-testid="Restaurants" />
)));

describe('App', () => {
  it('renders greeting message', () => {
    const { container } = render(<App />);

    expect(container).toHaveTextContent('Hello, world!');
  });

  it('renders <Counter> component', () => {
    const { queryByTestId } = render(<App />);

    expect(queryByTestId('Counter')).toBeInTheDocument();
  });

  it('renders <Restaurants> component', () => {
    const { queryByTestId } = render(<App />);

    expect(queryByTestId('Restaurants')).toBeInTheDocument();
  });
});
