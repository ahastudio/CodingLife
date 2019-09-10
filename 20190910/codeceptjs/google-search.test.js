Feature('Search on Google');

Scenario('search an interesting issue', I => {
  I.amOnPage('https://www.google.com/ncr');

  I.fillField('q', 'CodeceptJS');
  I.click('Google Search');

  I.waitForNavigation();

  I.see('CodeceptJS · Modern End 2 End Testing Framework for NodeJS');
  I.see('https://codecept.io');
  I.see('Modern End 2 End Testing Framework for NodeJS.');
});
