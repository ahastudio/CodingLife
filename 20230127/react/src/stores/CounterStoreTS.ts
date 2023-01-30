import { singleton } from 'tsyringe';

import { Store, Action } from 'usestore-ts';

@singleton()
@Store()
export default class CounterStoreTS {
  count = 0;

  @Action()
  increase(step = 1) {
    this.count += step;
  }

  @Action()
  decrease() {
    this.count -= 1;
  }
}
