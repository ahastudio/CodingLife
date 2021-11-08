import { render } from '@testing-library/react';

import CounterPanel from './CounterPanel';

import Counter from '../models/Counter';

const count = 37;
const counter = new Counter(count);

jest.mock('../store', () => ({
  useStore: {
    counter: jest.fn(() => [counter]),
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
