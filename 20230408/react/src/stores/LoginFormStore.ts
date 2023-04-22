import { singleton } from 'tsyringe';

import { Store, Action } from 'usestore-ts';

import { apiService } from '../services/ApiService';

@singleton()
@Store()
export default class LoginFormStore {
  email = '';

  password = '';

  error = false;

  accessToken = '';

  get valid() {
    return this.email.includes('@') && !!this.password;
  }

  @Action()
  changeEmail(email: string) {
    this.email = email;
  }

  @Action()
  changePassword(password: string) {
    this.password = password;
  }

  @Action()
  setAccessToken(accessToken: string) {
    this.accessToken = accessToken;
  }

  @Action()
  private setError() {
    this.error = true;
  }

  @Action()
  reset() {
    this.email = '';
    this.password = '';
    this.error = false;
    this.accessToken = '';
  }

  async login() {
    try {
      const accessToken = await apiService.login({
        email: this.email,
        password: this.password,
      });

      this.setAccessToken(accessToken);
    } catch (e) {
      this.setError();
    }
  }
}
