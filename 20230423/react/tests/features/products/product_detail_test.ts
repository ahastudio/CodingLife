Feature('Product Detail');

Before(({ backdoor, I }) => {
  backdoor.setupDatabase();

  I.login();
});

Scenario('Visit the product page', ({ I }) => {
  I.amOnPage('/products/0BV000PRO0001');

  I.see('CBCL 하트자수맨투맨');
  I.see('편하게 입을 수 있는 맨투맨');
});

Scenario('Product not found', ({ I }) => {
  I.amOnPage('/products/xxx');

  I.see('Error!');
});
