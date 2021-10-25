import { render, fireEvent } from '@testing-library/react';

import CounterButton from './CounterButton';

const count = 37;
const setCount = jest.fn();

jest.mock('../store', () => ({
  useStore: {
    count: jest.fn(() => [count, setCount]),
  },
}));

describe('CounterButton', () => {
  beforeEach(() => {
    jest.clearAllMocks();
  });

  it('renders the label text', () => {
    const { container } = render(<CounterButton />);

    expect(container).toHaveTextContent('Click me!');
  });

  describe('when click button', () => {
    it('updates `count` of store', () => {
      const { getByText } = render(<CounterButton />);

      fireEvent.click(getByText('Click me!'));

      expect(setCount).toBeCalledWith(count + 1);
    });
  });
});
