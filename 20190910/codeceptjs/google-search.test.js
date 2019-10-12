Feature('Search on Google');

Scenario('search an interesting issue', I => {
  I.amOnPage('https://www.google.com/ncr');

  I.fillField('q', 'CodeceptJS');
  I.click('Google Search');

  I.waitForNavigation();

  I.see('CodeceptJS · SuperCharged End 2 End Testing with ...');
  I.see('https://codecept.io');
  I.see('SuperCharged End 2 End Testing with WebDriver & Puppeteer.');
});
