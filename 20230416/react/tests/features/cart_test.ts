Feature('Cart');

Before(({ backdoor, I }) => {
  backdoor.setupDatabase();

  I.login();
});

Scenario('Empty cart', ({ I }) => {
  I.amOnPage('/cart');

  I.see('장바구니가 비었습니다');
});

Scenario('Add to cart', ({ I }) => {
  I.amOnPage('/products');

  I.click('CBCL 하트자수맨투맨');

  I.selectOption('컬러', 'blue');
  I.seeElement('//button[contains(., "+")]');

  I.click('장바구니에 담기');

  I.waitForText('장바구니에 담았습니다');

  I.amOnPage('/cart');

  I.see('CBCL 하트자수맨투맨\n(컬러: blue');
  I.see('합계\t128,000원');
});
