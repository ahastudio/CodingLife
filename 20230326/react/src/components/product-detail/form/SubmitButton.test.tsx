import { screen, fireEvent } from '@testing-library/react';

import { render } from '../../../test-helpers';

import SubmitButton from './SubmitButton';

let done = false;

const store = {
  get done() {
    return done;
  },
  addToCart: jest.fn(),
};

jest.mock('../../../hooks/useProductFormStore', () => () => [store, store]);

const context = describe;

describe('SubmitButton', () => {
  beforeEach(() => {
    jest.clearAllMocks();
  });

  context('when the button is ready', () => {
    beforeEach(() => {
      done = false;
    });

    it('renders a submit button', () => {
      render(<SubmitButton />);

      expect(screen.getByRole('button')).toHaveTextContent('장바구니에 담기');
    });

    context('when the button is clicked', () => {
      it('calls addToCart action', () => {
        render(<SubmitButton />);

        fireEvent.click(screen.getByRole('button'));

        expect(store.addToCart).toBeCalled();
      });
    });
  });

  context('when submitting is done', () => {
    beforeEach(() => {
      done = true;
    });

    it('renders a done message', () => {
      render(<SubmitButton />);

      screen.getByText(/장바구니에 담았습니다/);
    });
  });
});
