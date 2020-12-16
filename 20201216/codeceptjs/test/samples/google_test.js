Feature('Google Search');

Scenario('search “아샬”', ({ I }) => {
  // Given - 구글 웹 사이트에 접속해서
  I.amOnPage('https://www.google.com/ncr');

  // When - 아샬을 검색하면
  I.fillField('[name=q]', '아샬');
  I.click('Google Search');

  // Then - 아샬과 관련된 내용이 나와.
  I.see('코딩의 신');
});
