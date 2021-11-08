import { render, fireEvent } from '@testing-library/react';

import CounterButton from './CounterButton';

import Counter from '../models/Counter';

const count = 37;
const counter = new Counter(count);
const setCounter = jest.fn();

jest.mock('../store', () => ({
  useStore: {
    counter: jest.fn(() => [counter, setCounter]),
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

      expect(setCounter).toBeCalledWith(new Counter(count + 1));
    });
  });
});
