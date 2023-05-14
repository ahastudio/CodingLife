Feature('Create product');

Before(({ backdoor, I }) => {
  backdoor.setupDatabase();

  I.login();
});

Scenario('Create product', ({ I }) => {
  I.amOnPage('/');

  I.click('Products');

  I.click('상품 추가');

  I.selectOption('카테고리', 'bottom');

  I.fillField(
    '이미지 #1',
    'https://ahastudio.github.io/my-image-assets/images/cbcl-products/09.jpg',
  );

  I.fillField('상품명', '하읩보이');
  I.fillField('가격', '123456');

  I.click('옵션 추가');

  I.fillField('옵션 #1', '색상');
  I.fillField('옵션 아이템 #1-1', 'Black');

  I.click('아이템 추가');

  I.fillField('옵션 아이템 #1-2', 'White');

  I.click('옵션 추가');

  I.fillField('옵션 #2', '크기');
  I.fillField('옵션 아이템 #2-1', 'Free');

  I.fillField('설명', '입어요');

  I.click('등록');

  I.see('하읩보이');
  I.see('123,456원');
});
