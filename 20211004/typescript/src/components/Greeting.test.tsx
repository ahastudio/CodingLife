import { render } from '@testing-library/react';

import Greeting from './Greeting';

describe('Greeting', () => {
  it('renders without crash', () => {
    const { container } = render(<Greeting name="world" />);

    expect(container).toHaveTextContent('Hello, world!');
  });
});
