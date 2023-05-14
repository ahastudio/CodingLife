Feature('Product List');

Before(({ backdoor, I }) => {
  backdoor.setupDatabase();

  I.login();
});

Scenario('All products', ({ I }) => {
  I.amOnPage('/');

  I.click('Products');

  I.see('CBCL 하트자수맨투맨');
  I.see('CBCL 핀턱자수후드');
});
