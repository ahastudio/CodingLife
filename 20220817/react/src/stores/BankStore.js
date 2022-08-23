import { apiService } from '../services/ApiService';

export default class BankStore {
  constructor() {
    this.accountNumber = '';
    this.name = '';
    this.amount = 0;
    this.transactions = [];
  }

  async login({ accountNumber, password }) {
    try {
      const { accessToken, name, amount } = await apiService.postSession({
        accountNumber, password,
      });

      this.name = name;
      this.amount = amount;

      return accessToken;
    } catch (e) {
      // console.error(e);
      return '';
    }
  }
}

export const bankStore = new BankStore();
