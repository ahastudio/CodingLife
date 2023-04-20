import { singleton } from 'tsyringe';

import { Store, Action } from 'usestore-ts';

import { apiService } from '../services/ApiService';

@singleton()
@Store()
export default class OrderFormStore {
  name = '';

  address1 = '';

  address2 = '';

  postalCode = '';

  phoneNumber = '';

  get valid() {
    return !!this.name.trim()
      && !!this.address1.trim()
      && !!this.address2.trim()
      && !!this.postalCode.trim()
      && !!this.phoneNumber.trim();
  }

  async order({ merchantId, transactionId }: {
    merchantId: string;
    transactionId: string;
  }) {
    apiService.createOrder({
      receiver: {
        name: this.name,
        address1: this.address1,
        address2: this.address2,
        postalCode: this.postalCode,
        phoneNumber: this.phoneNumber,
      },
      payment: { merchantId, transactionId },
    });
  }

  @Action()
  changeName(name: string) {
    this.name = name;
  }

  @Action()
  changeAddress1(address1: string, postalCode: string) {
    this.address1 = address1;
    this.postalCode = postalCode;
  }

  @Action()
  changeAddress2(address2: string) {
    this.address2 = address2;
  }

  @Action()
  changePhoneNumber(phoneNumber: string) {
    this.phoneNumber = phoneNumber.replace(/[^0-9]/g, '');
  }
}
