import UserStore from './UserStore';

describe('UserStore', () => {
  describe('changeName', () => {
    it('updates name', () => {
      const name = 'Peter Parker';

      const userStore = new UserStore();

      userStore.changeName(name);

      expect(userStore.name.value).toBe(name);
    });
  });
});
