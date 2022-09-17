export default class TransferStore {
  constructor() {
    this.amounts = {
      a: 2_000,
      b: 0,
    };
  }

  amount(account) {
    return this.amounts[account];
  }

  transfer(from, to, amount) {
    if (amount > this.amounts[from]) {
      return;
    }

    this.amounts[from] -= amount;
    this.amounts[to] += amount;
  }
}
