import SignupFormStore from './SignupFormStore';

const signup = jest.fn();

jest.mock('../services/ApiService', () => ({
  get apiService() {
    return {
      signup,
    };
  },
}));

const context = describe;

describe('SignupFormStore', () => {
  let store: SignupFormStore;

  beforeEach(() => {
    jest.clearAllMocks();

    store = new SignupFormStore();
  });

  describe('changeEmail', () => {
    const email = 'tester@example.com';

    it('sets state', () => {
      store.changeEmail(email);

      expect(store.email).toBe(email);
    });
  });

  describe('changeName', () => {
    const name = 'Tester';

    it('sets state', () => {
      store.changeName(name);

      expect(store.name).toBe(name);
    });
  });

  describe('changePassword', () => {
    const password = 'password';

    it('sets state', () => {
      store.changePassword(password);

      expect(store.password).toBe(password);
    });
  });

  describe('changePasswordConfirmation', () => {
    const password = 'password';

    it('sets state', () => {
      store.changePasswordConfirmation(password);

      expect(store.passwordConfirmation).toBe(password);
    });
  });

  describe('reset', () => {
    beforeEach(() => {
      store.changeEmail('email');
      store.changeName('name');
      store.changePassword('password');
      store.changePasswordConfirmation('password');
      store.setAccessToken('accessToken');
    });

    it('clears state', () => {
      store.reset();

      expect(store.email).toBeFalsy();
      expect(store.name).toBeFalsy();
      expect(store.password).toBeFalsy();
      expect(store.passwordConfirmation).toBeFalsy();
      expect(store.accessToken).toBeFalsy();
    });
  });

  describe('valid', () => {
    const email = 'tester@example.com';
    const name = 'Tester';
    const password = 'password';

    context('when email and password are valid', () => {
      it('is true', async () => {
        store.changeEmail(email);
        store.changeName(name);
        store.changePassword(password);
        store.changePasswordConfirmation(password);

        expect(store.valid).toBeTruthy();
      });
    });

    context('when email is blank', () => {
      it('is false', async () => {
        store.changeEmail('');
        store.changeName(name);
        store.changePassword(password);
        store.changePasswordConfirmation(password);

        expect(store.valid).toBeFalsy();
      });
    });

    context('when email is invalid', () => {
      it('is false', async () => {
        store.changeEmail('xxx');
        store.changeName(name);
        store.changePassword(password);
        store.changePasswordConfirmation(password);

        expect(store.valid).toBeFalsy();
      });
    });

    context('when name is blank', () => {
      it('is false', async () => {
        store.changeEmail(email);
        store.changeName('');
        store.changePassword(password);
        store.changePasswordConfirmation(password);

        expect(store.valid).toBeFalsy();
      });
    });

    context('when password is blank', () => {
      it('is false', async () => {
        store.changeEmail(email);
        store.changeName(name);
        store.changePassword('');
        store.changePasswordConfirmation(password);

        expect(store.valid).toBeFalsy();
      });
    });

    context('when password confirmation is not same', () => {
      it('is false', async () => {
        store.changeEmail(email);
        store.changeName(name);
        store.changePassword(password);
        store.changePasswordConfirmation(`${password}xxx`);

        expect(store.valid).toBeFalsy();
      });
    });
  });

  describe('signup', () => {
    const email = 'tester@example.com';
    const name = 'Tester';
    const password = 'password';
    const accessToken = 'accessToken';

    beforeEach(() => {
      store.changeEmail(email);
      store.changeName(name);
      store.changePassword(password);
      store.changePasswordConfirmation(password);
    });

    context('when request is successful', () => {
      beforeEach(() => {
        signup.mockResolvedValue(accessToken);
      });

      it('sets access token', async () => {
        await store.signup();

        expect(store.accessToken).toBe(accessToken);
        expect(store.error).toBeFalsy();

        expect(signup).toBeCalledWith({ email, name, password });
      });
    });

    context('when request is failed', () => {
      beforeEach(() => {
        signup.mockRejectedValue(new Error('Bad Request'));
      });

      it('sets error', async () => {
        await store.signup();

        expect(store.accessToken).toBeFalsy();
        expect(store.error).toBeTruthy();

        expect(signup).toBeCalledWith({ email, name, password });
      });
    });
  });
});
