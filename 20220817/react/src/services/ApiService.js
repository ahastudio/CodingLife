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
}

export const apiService = new ApiService();
