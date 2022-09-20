Feature('로그인');

// Given
Before(({ I }) => {
  I.setupDatabase();
  I.changeAmount({ userId: 1, amount: 1000 });
  I.changeAmount({ userId: 2, amount: 2000 });
});

Scenario('1번 사용자 로그인', ({ I }) => {
  // When
  I.amOnPage('/');

  I.click('로그인');

  I.fillField('계좌 번호', '1234');
  I.fillField('패스워드', 'password');
  I.click('[type=submit]');

  // Then
  I.click('잔액 확인');

  I.see('계좌번호: 1234');
  I.see('잔액: 1,000원');
});

Scenario('2번 사용자 로그인', ({ I }) => {
  // When
  I.amOnPage('/');

  I.click('로그인');

  I.fillField('계좌 번호', '1234567890');
  I.fillField('패스워드', 'password');
  I.click('[type=submit]');

  // Then
  I.click('잔액 확인');

  I.see('계좌번호: 1234567890');
  I.see('잔액: 2,000원');
});
