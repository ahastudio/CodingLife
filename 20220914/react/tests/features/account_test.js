Feature('잔액 확인 - 고객은 얼마를 쓸 수 있는지 알기 위해 본인 계좌의 잔액을 확인할 수 있다.');

// Given
Before(({ I }) => {
  I.setupDatabase();

  I.amOnPage('/');

  // TODO: 로그인
});

Scenario('잔액이 없는 경우', ({ I }) => {
  // Given
  I.changeAmount({ userId: 1, amount: 0 });
  I.amOnPage('/');

  // When
  I.click('잔액 확인');

  // Then
  I.see('잔액이 없습니다');
});

Scenario('잔액이 있는 경우', ({ I }) => {
  // Given
  I.changeAmount({ userId: 1, amount: 123000 });
  I.amOnPage('/');

  // When
  I.click('잔액 확인');

  // Then
  I.see('잔액: 123,000원');
});
