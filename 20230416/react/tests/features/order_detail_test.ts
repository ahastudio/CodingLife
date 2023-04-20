Feature('Order Detail');

Before(({ backdoor, I }) => {
  backdoor.setupDatabase();

  I.login();
});

Scenario('Show order detail', ({ I }) => {
  I.amOnPage('/');

  I.click('Orders');

  I.click('0BV000ODR0001');

  I.see('CBCL 하트자수맨투맨');
  I.see('CBCL 레귤러핏 야구점퍼');
});
