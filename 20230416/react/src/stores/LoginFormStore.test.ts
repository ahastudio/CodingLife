test('', () => {
  //
});

// import LoginFormStore from './LoginFormStore';

// const login = jest.fn();

// jest.mock('../services/ApiService', () => ({
//   get apiService() {
//     return {
//       login,
//     };
//   },
// }));

// const context = describe;

// describe('LoginFormStore', () => {
//   let store: LoginFormStore;

//   beforeEach(() => {
//     jest.clearAllMocks();

//     store = new LoginFormStore();
//   });

//   describe('changeEmail', () => {
//     const email = 'tester@example.com';

//     it('sets state', () => {
//       store.changeEmail(email);

//       expect(store.email).toBe(email);
//     });
//   });

//   describe('changePassword', () => {
//     const password = 'password';

//     it('sets state', () => {
//       store.changePassword(password);

//       expect(store.password).toBe(password);
//     });
//   });

//   describe('reset', () => {
//     beforeEach(() => {
//       store.changeEmail('email');
//       store.changePassword('password');
//       store.setAccessToken('accessToken');
//     });

//     it('clears state', () => {
//       store.reset();

//       expect(store.email).toBeFalsy();
//       expect(store.password).toBeFalsy();
//       expect(store.accessToken).toBeFalsy();
//     });
//   });

//   describe('valid', () => {
//     const email = 'tester@example.com';
//     const password = 'password';

//     context('when email and password are valid', () => {
//       it('is true', async () => {
//         store.changeEmail(email);
//         store.changePassword(password);

//         expect(store.valid).toBeTruthy();
//       });
//     });

//     context('when email is blank', () => {
//       it('is false', async () => {
//         store.changeEmail('');
//         store.changePassword(password);

//         expect(store.valid).toBeFalsy();
//       });
//     });

//     context('when email is invalid', () => {
//       it('is false', async () => {
//         store.changeEmail('xxx');
//         store.changePassword(password);

//         expect(store.valid).toBeFalsy();
//       });
//     });

//     context('when password is blank', () => {
//       it('is false', async () => {
//         store.changeEmail(email);
//         store.changePassword('');

//         expect(store.valid).toBeFalsy();
//       });
//     });
//   });

//   describe('login', () => {
//     const email = 'tester@example.com';
//     const password = 'password';
//     const accessToken = 'accessToken';

//     beforeEach(() => {
//       store.changeEmail(email);
//       store.changePassword(password);
//     });

//     context('when request is successful', () => {
//       beforeEach(() => {
//         login.mockResolvedValue(accessToken);
//       });

//       it('sets access token', async () => {
//         await store.login();

//         expect(store.accessToken).toBe(accessToken);
//         expect(store.error).toBeFalsy();

//         expect(login).toBeCalledWith({ email, password });
//       });
//     });

//     context('when request is failed', () => {
//       beforeEach(() => {
//         login.mockRejectedValue(new Error('Bad Request'));
//       });

//       it('sets error', async () => {
//         await store.login();

//         expect(store.error).toBeTruthy();

//         expect(login).toBeCalledWith({ email, password });
//       });
//     });
//   });
// });
