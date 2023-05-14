Feature('Category List');

Before(({ backdoor, I }) => {
  backdoor.setupDatabase();

  I.login();
});

Scenario('Show category list', ({ I }) => {
  I.amOnPage('/');

  I.click('Categories');

  I.see('top');
  I.see('outer');
});
