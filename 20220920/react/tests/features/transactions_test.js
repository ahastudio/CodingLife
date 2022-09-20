Feature('거래 내역 확인 - 고객은 소비 계획을 세울 수 있도록 거래 내역을 확인할 수 있다.');

// Given
Before(({ I }) => {
  I.setupDatabase();
  I.changeAmount({ userId: 1, amount: 1000000 });
});

Scenario('거래 내역이 없는 경우', ({ I }) => {
  // Given
  I.login('1234');

  // When
  I.amOnPage('/');
  I.click('거래 내역 확인');

  // Then
  I.see('거래 내역이 없습니다');
});

Scenario('내가 보낸 거래 내역이 있는 경우', ({ I }) => {
  // Given
  I.login('1234');
  I.transfer({ to: '1234567890', amount: 5000, name: '테스트' });

  // When
  I.amOnPage('/');
  I.click('거래 내역 확인');

  // Then
  I.see('송금\t1234567890\t5,000원');
});

Scenario('내가 받은 거래 내역이 있는 경우', ({ I }) => {
  // Given
  I.login('1234');
  I.transfer({ to: '1234567890', amount: 5000, name: '테스트' });
  I.login('1234567890');

  // When
  I.amOnPage('/');
  I.click('거래 내역 확인');

  // Then
  I.see('입금\t테스트\t5,000원');
});
