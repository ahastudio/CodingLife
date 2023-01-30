import { singleton } from 'tsyringe';

import ObjectStore from './ObjectStore';

@singleton()
export default class CounterStore extends ObjectStore {
  count = 0;

  /**
   * 카운트를 증가시킨다.
   */
  increase(step = 1) {
    this.count += step;
    this.publish();
  }

  /**
   * 카운트를 감소시킨다.
   */
  decrease(step = 1) {
    this.count -= step;
    this.publish();
  }
}
