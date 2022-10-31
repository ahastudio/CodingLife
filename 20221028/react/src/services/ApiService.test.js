import ApiService from './ApiService';

import fixtures from '../fixtures';

jest.mock('axios', () => ({
  get() {
    return {
      data: fixtures.posts,
    };
  },
}));

describe('ApiService', () => {
  let apiService;

  beforeEach(() => {
    apiService = new ApiService();
  });

  describe('fetchPosts', () => {
    it('returns posts', async () => {
      const posts = await apiService.fetchPosts();

      expect(posts).toEqual(fixtures.posts);
    });
  });
});
