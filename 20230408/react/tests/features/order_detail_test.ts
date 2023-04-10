Feature('Order Detail');

Before(({ backdoor, I }) => {
  backdoor.setupDatabase();

  I.login();
});

Scenario('Orders of tester', ({ I }) => {
  I.amOnPage('/');

  I.click('Orders');

  I.click('0BV000ODR0001');

  I.see('');
});
