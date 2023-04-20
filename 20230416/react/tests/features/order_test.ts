Feature('Order');

Before(({ backdoor, I }) => {
  backdoor.setupDatabase();

  I.login();
});

Scenario('Order', ({ I }) => {
  I.amOnPage('/products');

  I.click('CBCL 하트자수맨투맨');

  I.selectOption('컬러', 'blue');
  I.seeElement('//button[contains(., "+")]');

  I.click('장바구니에 담기');

  I.waitForText('장바구니에 담았습니다');

  I.amOnPage('/cart');

  I.click('주문하기');

  I.fillField('이름', '홍길동');

  I.click('우편번호 검색');

  I.waitForElement('[title="우편번호서비스 레이어 프레임"]');

  I.switchTo('#address-search-container iframe');
  I.switchTo('iframe');

  I.fillField('#region_name', '상원12길 34');
  I.pressKey('Enter');

  I.wait(3);

  I.click('.txt_addr');

  I.switchTo();

  I.dontSee('#address-search-container');

  I.fillField('상세 주소', 'ㅇㅇㅇ호');
  I.fillField('전화번호', '010-1234-5678');

  // TODO: 주소 입력 처리 기능 개발을 돕기 위한 테스트용 기다림. 개발 끝나면 삭제할 것!
  I.wait(100_000);
});
