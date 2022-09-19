import { waitFor } from '@testing-library/react';
import BankStore from './BankStore';

// 1. 직접 mocking
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

// 2. __mocks__ 폴더 이용해서 mocking
// jest.mock('../services/ApiService');

// 3. MSW를 이용해서 mocking
// --> 현재 이걸 사용 중!

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

  describe('requestTransfer', () => {
    context('when request is successful', () => {
      async function request() {
        await bankStore.requestTransfer({
          to: '1234',
          amount: 100,
          name: 'Test',
        });
      }

      it('sets transfer state to “processing” and “success”', async () => {
        request();

        expect(bankStore.isTransferProcessing).toBeTruthy();

        await waitFor(() => {
          expect(bankStore.isTransferSuccess).toBeTruthy();
        });
      });

      it('doesn\'t set error message', async () => {
        request();

        expect(bankStore.errorMessage).toBeFalsy();
      });
    });

    context('when request is failed', () => {
      async function request() {
        await bankStore.requestTransfer({
          to: '1234',
          amount: -100,
          name: 'Test',
        });
      }

      it('sets transfer state to “processing” and “fail”', async () => {
        request();

        expect(bankStore.isTransferProcessing).toBeTruthy();

        await waitFor(() => {
          expect(bankStore.isTransferFail).toBeTruthy();
        });
      });

      it('sets error message', async () => {
        await request();

        expect(bankStore.errorMessage).toBeTruthy();
      });
    });
  });
});
