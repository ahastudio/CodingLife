Feature('Order List');

Before(({ backdoor, I }) => {
  backdoor.setupDatabase();

  I.login();
});

Scenario('Show order list', ({ I }) => {
  I.amOnPage('/');

  I.click('Orders');

  I.see('0BV000ODR0001');
  I.see('CBCL 하트자수맨투맨');
  I.see('653,000원');
});
