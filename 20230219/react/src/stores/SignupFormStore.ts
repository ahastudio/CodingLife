import { singleton } from 'tsyringe';

import { Store, Action } from 'usestore-ts';

import { apiService } from '../services/ApiService';

@singleton()
@Store()
export default class SignupFormStore {
  email = '';

  password = '';

  passwordConfirmation = '';

  error = false;

  accessToken = '';

  get valid() {
    return this.email.includes('@')
      && this.password.length >= 4
      && this.password === this.passwordConfirmation;
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
  changePasswordConfirmation(password: string) {
    this.passwordConfirmation = password;
  }

  @Action()
  setAccessToken(accessToken: string) {
    this.accessToken = accessToken;
  }

  @Action()
  setError() {
    this.error = true;
  }

  @Action()
  reset() {
    this.email = '';
    this.password = '';
    this.passwordConfirmation = '';
    this.error = false;
    this.accessToken = '';
  }

  async signup() {
    try {
      const accessToken = await apiService.signup({
        email: this.email,
        password: this.password,
      });

      this.setAccessToken(accessToken);
    } catch (e) {
      this.setError();
    }
  }
}
