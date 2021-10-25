import { render } from '@testing-library/react';

import NameField from './NameField';

const name = 'John';
const setName = jest.fn();

jest.mock('../store', () => ({
  useStore: {
    name: jest.fn(() => [name, setName]),
  },
}));

describe('NameField', () => {
  beforeEach(() => {
    jest.clearAllMocks();
  });

  it('renders an input control', () => {
    const { getByRole } = render(<NameField />);

    expect(getByRole('textbox')).toHaveValue('John');
  });
});
