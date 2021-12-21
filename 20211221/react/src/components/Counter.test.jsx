import { render, fireEvent } from '@testing-library/react';

const dispatch = jest.fn();

const counter = {
  count: 37,
};

jest.doMock('react-redux', () => ({
  useDispatch: jest.fn(() => dispatch),
  useSelector: (selector) => selector({
    counter,
  }),
}));

const Counter = require('./Counter').default;

beforeEach(() => {
  jest.clearAllMocks();
});

describe('Counter', () => {
  it('renders counting number', () => {
    const { container } = render(<Counter />);

    expect(container).toHaveTextContent('37');
  });

  it('listens for plus button click event.', () => {
    const { getByText } = render(<Counter />);

    fireEvent.click(getByText('[+]'));

    expect(dispatch).toBeCalledWith({
      type: 'counter/increment',
    });
  });

  it('listens for minus button click event.', () => {
    const { getByText } = render(<Counter />);

    fireEvent.click(getByText('[-]'));

    expect(dispatch).toBeCalledWith({
      type: 'counter/decrement',
    });
  });

  it('listens for [+2] button click event.', () => {
    const { getByText } = render(<Counter />);

    fireEvent.click(getByText('[+2]'));

    expect(dispatch).toBeCalledWith({
      type: 'counter/incrementByAmount',
      payload: { amount: 2 },
    });
  });
});
