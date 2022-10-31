import BoardSotre from './BoardStore';

import fixtures from '../fixtures';

jest.mock('../services/ApiService', () => ({
  apiService: {
    async fetchPosts() {
      return fixtures.posts;
    },
  },
}));

describe('BoardStore', () => {
  let store;

  beforeEach(() => {
    store = new BoardSotre();
  });

  describe('fetchPosts', () => {
    it('fetches and sets posts', async () => {
      await store.fetchPosts();

      expect(store.posts).toEqual(fixtures.posts);
    });
  });
});
