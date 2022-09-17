export default class AccountStore {
  constructor({ account, amount = 0, transactions = [] }) {
    this.account = account;
    this.amount = amount;
    this.transactions = transactions;
  }
}
