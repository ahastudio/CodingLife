/* global actor */

const backdoorBaseUrl = 'http://localhost:8000/backdoor';

module.exports = () => actor({
  setupDatabase() {
    this.amOnPage(`${backdoorBaseUrl}/setup-database`);
    this.waitForText('OK');
  },
  changeAmount({ userId, amount }) {
    this.amOnPage([
      `${backdoorBaseUrl}/change-amount`,
      `?userId=${userId}&amount=${amount}`,
    ].join(''));
    this.waitForText('OK');
  },
  login(accountNumber) {
    this.amOnPage('/login');

    this.fillField('계좌 번호', accountNumber);
    this.fillField('패스워드', 'password');
    this.click('[type=submit]');

    this.waitForText('로그아웃');
  },
  transfer({ to, amount, name }) {
    this.amOnPage('/transfer');

    this.fillField('받을 분 계좌 번호', to);
    this.fillField('보낼 금액', amount);
    this.fillField('받는 분께 표시할 이름', name);
    this.click('보내기');

    this.waitForText('계좌 이체 성공');
  },
});
