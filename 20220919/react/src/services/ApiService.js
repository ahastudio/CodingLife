/* eslint-disable class-methods-use-this */

import axios from 'axios';

import config from '../config';

const baseUrl = config.apiBaseUrl;

export default class ApiService {
  async postSession({ accountNumber, password }) {
    const url = `${baseUrl}/session`;
    const { data } = await axios.post(url, { accountNumber, password });
    return {
      accessToken: data.accessToken,
      name: data.name,
      amount: data.amount,
    };
  }

  async fetchAccount() {
    const url = `${baseUrl}/accounts/me`;
    // TODO: access token을 header로 넘겨줄 것!
    const { data } = await axios.get(url);
    return {
      name: data.name,
      accountNumber: data.accountNumber,
      amount: data.amount,
    };
  }

  async fetchTransactions() {
    const url = `${baseUrl}/transactions`;
    // TODO: access token을 header로 넘겨줄 것!
    const { data } = await axios.get(url);
    const { transactions } = data;
    return transactions;
  }

  async createTransaction({ to, amount, name }) {
    const url = `${baseUrl}/transactions`;
    await axios.post(url, { to, amount, name });
  }
}

export const apiService = new ApiService();
