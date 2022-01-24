import { render, fireEvent, act } from '@testing-library/react';

import Users from './Users';

// Just Hack! :)
const context = describe;

let data: {
  users: any[] | null;
  loading: boolean;
  error?: any;
};

const assignUser = jest.fn(async () => ({
  id: 1,
  assignee: {
    user: 1,
  },
}));

jest.mock('../hooks/useUsers', () => jest.fn(() => data));
jest.mock('../hooks/useAssignUser', () => jest.fn(() => ({
  assignUser,
})));

describe('Users', () => {
  beforeEach(() => {
    assignUser.mockClear();
  });

  context('when loading is complete', () => {
    beforeEach(() => {
      data = {
        users: [
          { id: 1, name: 'Peter Parker', avatar: { imageUrl: 'https://...' } },
        ],
        loading: false,
      };
    });

    it('renders a list of user', () => {
      const { container } = render(<Users />);

      expect(container).toHaveTextContent('Peter Parker');
    });

    it('listens for clicking event', async () => {
      const { container, getByText } = render(<Users />);

      await act(async () => {
        fireEvent.click(getByText('Peter Parker'));
      });

      expect(assignUser).toBeCalled();

      expect(container).toHaveTextContent('{"id":1,"assignee":{"user":');
    });
  });

  context('when loading is not complete', () => {
    beforeEach(() => {
      data = {
        users: null,
        loading: true,
      };
    });

    it('renders loading message', () => {
      const { container } = render(<Users />);

      expect(container).toHaveTextContent('Loading...');
    });
  });

  context('when error occurs', () => {
    beforeEach(() => {
      data = {
        users: null,
        loading: false,
        error: true,
      };
    });

    it('renders error message', () => {
      const { container } = render(<Users />);

      expect(container).toHaveTextContent('Error!');
    });
  });
});
