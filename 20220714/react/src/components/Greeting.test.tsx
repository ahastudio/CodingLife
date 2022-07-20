import { render, screen } from '@testing-library/react';

import Greeting from './Greeting';

import NameField from '../models/NameField';

const context = describe;

const mockState = {
  name: new NameField(),
};

jest.mock('../hooks/useUserStore', () => () => [
  mockState,
]);

describe('Greeting', () => {
  beforeEach(() => {
    mockState.name = new NameField();
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
      mockState.name = new NameField(name);
    });

    it('renders greeting message with name', () => {
      render(<Greeting />);

      screen.getByText(new RegExp(`Hello, ${name}`));
    });
  });
});
