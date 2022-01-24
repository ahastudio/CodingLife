import { render } from '@testing-library/react';

import App from './App';

jest.mock('./components/Users', () => jest.fn(() => (
  <div data-testid="Users" />
)));

describe('App', () => {
  it('renders greeting message', () => {
    const { container } = render(<App />);

    expect(container).toHaveTextContent('Hello, world!');
  });

  it('renders `Users` component`', () => {
    const { getByTestId } = render(<App />);

    getByTestId('Users');
  });
});
