import { Store, Action } from './core';

import NameField from '../models/NameField';

@Store()
export default class UserStore {
  name = new NameField();

  @Action()
  changeName(name: string) {
    this.name = this.name.update(name);
  }
}
