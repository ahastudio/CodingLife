import { render, screen, fireEvent } from '@testing-library/react';

import Greeting from './Greeting';

import NameField from '../models/NameField';

const context = describe;

const mocks = {
  name: new NameField(),
  changeName: jest.fn(),
};

jest.mock('../hooks/useUserStore', () => () => ({
  get name() {
    return mocks.name;
  },
  changeName: mocks.changeName,
}));

describe('Greeting', () => {
  beforeEach(() => {
    mocks.name = new NameField();

    jest.clearAllMocks();
  });

  context('without name', () => {
    it('renders greeting message with `world`', () => {
      render(<Greeting />);

      screen.getByText(/Hello, world/);
    });
  });

  context('with name', () => {
    const name = 'Peter Parker';

    beforeEach(() => {
      mocks.name = new NameField(name);
    });

    it('renders greeting message with name', () => {
      render(<Greeting />);

      screen.getByText(new RegExp(`Hello, ${name}`));
    });
  });

  context('when input control is changed', () => {
    const name = 'Peter Parker';

    it('calls `onChange`', () => {
      render(<Greeting />);

      fireEvent.change(screen.getByLabelText('Name'), {
        target: { value: name },
      });

      expect(mocks.changeName).toBeCalledWith(name);
    });
  });
});
