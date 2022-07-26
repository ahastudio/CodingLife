import { Store, Action } from 'usestore-ts';

@Store()
export default class CounterStore {
  count = 0;

  @Action()
  increase() {
    this.count += 1;
  }

  @Action()
  reset() {
    this.count = 0;
  }
}
