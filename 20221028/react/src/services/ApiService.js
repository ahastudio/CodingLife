/* eslint-disable class-methods-use-this */

import axios from 'axios';

const baseUrl = 'http://localhost:3000';

export default class ApiService {
  async fetchPosts() {
    const url = `${baseUrl}/posts`;
    const { data } = await axios.get(url);
    return data;
  }
}

export const apiService = new ApiService();
