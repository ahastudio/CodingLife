import OrderFormStore from './OrderFormStore';

const signup = jest.fn();

jest.mock('../services/ApiService', () => ({
  get apiService() {
    return {
      signup,
    };
  },
}));

const context = describe;

describe('OrderFormStore', () => {
  let store: OrderFormStore;

  beforeEach(() => {
    jest.clearAllMocks();

    store = new OrderFormStore();
  });

  describe('changeName', () => {
    const name = 'Tester';

    it('sets state', () => {
      store.changeName(name);

      expect(store.name).toBe(name);
    });
  });

  describe('changeAddress1', () => {
    const address1 = '서울 성동구 상원12길 34';
    const postalCode = '04790';

    it('sets state', () => {
      store.changeAddress1(address1, postalCode);

      expect(store.address1).toBe(address1);
      expect(store.postalCode).toBe(postalCode);
    });
  });

  describe('changeAddress2', () => {
    const address2 = '000호';

    it('sets state', () => {
      store.changeAddress2(address2);

      expect(store.address2).toBe(address2);
    });
  });

  describe('changePhoneNumber', () => {
    const phoneNumber = '01012345678';

    it('sets state', () => {
      store.changePhoneNumber(phoneNumber);

      expect(store.phoneNumber).toBe(phoneNumber);
    });
  });

  describe('valid', () => {
    const name = 'Tester';
    const address1 = '서울 성동구 상원12길 34';
    const address2 = '000호';
    const postalCode = '04790';
    const phoneNumber = '01012345678';

    context('when all things are valid', () => {
      it('is true', async () => {
        store.changeName(name);
        store.changeAddress1(address1, postalCode);
        store.changeAddress2(address2);
        store.changePhoneNumber(phoneNumber);

        expect(store.valid).toBeTruthy();
      });
    });

    context('when name is blank', () => {
      it('is false', async () => {
        store.changeAddress1(address1, postalCode);
        store.changeAddress2(address2);
        store.changePhoneNumber(phoneNumber);

        expect(store.valid).toBeFalsy();
      });
    });

    context('when address1(and postal code) is blank', () => {
      it('is false', async () => {
        store.changeName(name);
        store.changeAddress2(address2);
        store.changePhoneNumber(phoneNumber);

        expect(store.valid).toBeFalsy();
      });
    });

    context('when address2 is blank', () => {
      it('is false', async () => {
        store.changeName(name);
        store.changeAddress1(address1, postalCode);
        store.changePhoneNumber(phoneNumber);

        expect(store.valid).toBeFalsy();
      });
    });

    context('when phone number is blank', () => {
      it('is false', async () => {
        store.changeName(name);
        store.changeAddress1(address1, postalCode);
        store.changeAddress2(address2);

        expect(store.valid).toBeFalsy();
      });
    });
  });
});
