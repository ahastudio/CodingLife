import BankStore from './BankStore';

// jest.mock('../services/ApiService', () => ({
//   apiService: {
//     async postSession({ accountNumber, password }) {
//       if (accountNumber === '1234' && password === 'password') {
//         return {
//           accessToken: 'ACCESS.TOKEN',
//           name: 'Tester',
//           amount: 100_000,
//         };
//       }
//       throw new Error('Login failed');
//     },
//   },
// }));

// jest.mock('../services/ApiService');

const context = describe;

describe('BankStore', () => {
  let bankStore;

  beforeEach(() => {
    bankStore = new BankStore();
  });

  describe('login', () => {
    context('with correct account number and password', () => {
      it('loads account information', async () => {
        await bankStore.login({ accountNumber: '1234', password: 'password' });

        expect(bankStore.name).toBe('Tester');
        expect(bankStore.amount).toBe(100_000);
      });
    });

    context('with incorrect account number', () => {
      it('loads account information', async () => {
        await bankStore.login({ accountNumber: 'xxx', password: 'password' });

        expect(bankStore.name).toBeFalsy();
        expect(bankStore.amount).toBe(0);
      });
    });
  });

  describe('fetchAccount', () => {
    it('sets account information', async () => {
      await bankStore.fetchAccount();

      expect(bankStore.name).toBe('Tester');
      expect(bankStore.accountNumber).toBe('1234');
      expect(bankStore.amount).toBe(100_000);
    });
  });
});
