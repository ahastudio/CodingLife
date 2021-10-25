import { render } from '@testing-library/react';

import App from './App';

describe('App', () => {
  it('renders title', () => {
    const { container } = render(<App />);

    expect(container).toHaveTextContent('Hello, world!');
  });
});
