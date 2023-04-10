Feature('Product Detail');

Scenario('Visit the product page', ({ I }) => {
  I.amOnPage('/');

  I.click('Products');

  I.click('CBCL 하트자수맨투맨');

  I.see('편하게 입을 수 있는 맨투맨');
});

Scenario('Product not found', ({ I }) => {
  I.amOnPage('/products/xxx');

  I.see('Error!');
});
