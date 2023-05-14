Feature('Edit product');

Before(({ backdoor, I }) => {
  backdoor.setupDatabase();

  I.login();
});

Scenario('Edit product', ({ I }) => {
  I.amOnPage('/');

  I.click('Products');

  I.click('수정');

  I.selectOption('카테고리', 'outer');

  I.click('이미지 추가');
  I.fillField(
    '이미지 #2',
    'https://ahastudio.github.io/my-image-assets/images/cbcl-products/03.jpg',
  );
  I.click('이미지 삭제');

  I.fillField('상품명', '탑이지만 아우터');
  I.fillField('가격', '123456');

  I.click('옵션 삭제');

  I.click('아이템 추가');
  I.click('아이템 추가');

  I.fillField('옵션 아이템 #1-1', 'S');
  I.fillField('옵션 아이템 #1-2', 'M');
  I.fillField('옵션 아이템 #1-3', 'L');

  I.click('옵션 추가');

  I.fillField('옵션 #2', '색상');
  I.fillField('옵션 아이템 #2-1', '빨강');

  I.fillField('설명', '이것은 탑인가 아우터인가');

  I.click('변경');

  I.see('탑이지만 아우터');
  I.see('123,456원');
});
