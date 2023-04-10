import { renderHook, waitFor } from '@testing-library/react';

import useCheckAccessToken from './useCheckAccessToken';

const setAccessToken = jest.fn();
const fetchCurrentUser = jest.fn();

jest.mock('./useAccessToken', () => () => ({
  accessToken: '',
  setAccessToken,
}));

jest.mock('../services/ApiService', () => ({
  get apiService() {
    return {
      fetchCurrentUser,
    };
  },
}));

const context = describe;

describe('useCheckAccessToken', () => {
  beforeEach(() => {
    jest.clearAllMocks();
  });

  context('when fetching is successful', () => {
    beforeEach(() => {
      const user = 'USER';
      fetchCurrentUser.mockResolvedValue(user);
    });

    it("doesn't call “setAccess”", () => {
      renderHook(() => useCheckAccessToken());

      expect(setAccessToken).not.toBeCalled();
    });
  });

  context('when fetching is failed', () => {
    beforeEach(() => {
      fetchCurrentUser.mockRejectedValue(new Error('Bad Request'));
    });

    it('calls “setAccess” with empty string', async () => {
      renderHook(() => useCheckAccessToken());

      await waitFor(() => {
        expect(setAccessToken).toBeCalledWith('');
      });
    });
  });
});
