Feature('Homepage');

Scenario('Visit the home page', ({ I }) => {
  I.amOnPage('/');

  I.see('와일드 코딩');
});
