import { apiService } from '../services/ApiService';

export default class BankStore {
  constructor() {
    this.listeners = new Set();

    this.accountNumber = '';
    this.name = '';
    this.amount = 0;
    this.transactions = [];
  }

  subscribe(listener) {
    this.listeners.add(listener);
  }

  unsubscribe(listener) {
    this.listeners.delete(listener);
  }

  publish() {
    this.listeners.forEach((listener) => listener());
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

  async fetchAccount() {
    const { name, accountNumber, amount } = await apiService.fetchAccount();

    this.name = name;
    this.accountNumber = accountNumber;
    this.amount = amount;

    this.publish();
  }
}

export const bankStore = new BankStore();
