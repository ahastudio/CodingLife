Feature('Google');

Scenario('Search “CodeceptJS”', ({ I }) => {
  I.amOnPage('https://www.google.com/ncr');
  I.fillField('[name="q"]', 'CodeceptJS');
  I.click('Google Search');
  I.see('codecept.io');
});
