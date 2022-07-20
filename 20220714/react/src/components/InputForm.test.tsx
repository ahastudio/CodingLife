import { render, screen, fireEvent } from '@testing-library/react';

import InputForm from './InputForm';

import NameField from '../models/NameField';

const context = describe;

const mockState = {
  name: new NameField(),
};

const mockStore = {
  changeName: jest.fn(),
};

jest.mock('../hooks/useUserStore', () => () => [
  mockState,
  mockStore,
]);

describe('InputForm', () => {
  beforeEach(() => {
    mockState.name = new NameField();

    jest.clearAllMocks();
  });

  context('when input control is changed', () => {
    const name = 'Peter Parker';

    it('calls `onChange`', () => {
      render(<InputForm />);

      fireEvent.change(screen.getByLabelText('Name'), {
        target: { value: name },
      });

      expect(mockStore.changeName).toBeCalledWith(name);
    });
  });

  context('when input control is not changed', () => {
    const name = mockState.name.value;

    it('calls `onChange`', () => {
      render(<InputForm />);

      fireEvent.change(screen.getByLabelText('Name'), {
        target: { value: name },
      });

      expect(mockStore.changeName).not.toBeCalled();
    });
  });
});
