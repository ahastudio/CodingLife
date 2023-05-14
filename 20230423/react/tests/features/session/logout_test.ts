Feature('Log out');

Before(({ backdoor, I }) => {
  backdoor.setupDatabase();

  I.login();
});

Scenario('Logout success', ({ I }) => {
  I.amOnPage('/');

  I.click('Logout');

  I.waitForText('로그인');

  I.dontSee('Logout');
});
