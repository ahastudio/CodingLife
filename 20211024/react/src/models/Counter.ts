export default class Counter {
  private value: number;

  constructor(value: number) {
    this.value = value;
  }

  increase() {
    return new Counter(this.value + 1);
  }

  count() {
    return this.value;
  }
}
