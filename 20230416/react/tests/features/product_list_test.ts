Feature('Product List');

Scenario('All products', ({ I }) => {
  I.amOnPage('/');

  I.click('Products');

  I.see('CBCL 하트자수맨투맨');
  I.see('CBCL 핀턱자수후드');
});

Scenario('Category', ({ I }) => {
  I.amOnPage('/');

  I.click('top');

  I.see('CBCL 하트자수맨투맨');
  I.dontSee('CBCL 핀턱자수후드');

  I.click('outer');

  I.dontSee('CBCL 하트자수맨투맨');
  I.see('CBCL 핀턱자수후드');

  I.click('bottom');

  I.dontSee('CBCL 하트자수맨투맨');
  I.dontSee('CBCL 핀턱자수후드');
});
