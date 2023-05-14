Feature('Change order status');

Before(({ backdoor, I }) => {
  backdoor.setupDatabase();

  I.login();
});

Scenario('Change order status', ({ I }) => {
  I.amOnPage('/orders/0BV000ODR0001');

  I.see('결제 완료');

  I.click('상태 변경');

  I.selectOption('상태', '배송 준비');

  I.click('변경');

  I.waitForText('Order Detail');

  I.see('배송 준비');
});
