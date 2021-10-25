import { render } from '@testing-library/react';

import CounterPanel from './CounterPanel';

const count = 37;

jest.mock('../store', () => ({
  useStore: {
    count: jest.fn(() => [count]),
  },
}));

describe('CounterPanel', () => {
  beforeEach(() => {
    jest.clearAllMocks();
  });

  it('renders the counting number', () => {
    const { container } = render(<CounterPanel />);

    expect(container).toHaveTextContent(`Count: ${count}`);
  });
});
