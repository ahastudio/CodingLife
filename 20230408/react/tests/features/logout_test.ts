Feature('Log out');

Before(({ backdoor, I }) => {
  backdoor.setupDatabase();

  I.login();
});

Scenario('Logout success', ({ I }) => {
  I.amOnPage('/');

  I.see('Cart');

  I.click('Logout');

  I.waitForText('Login');

  I.dontSee('Cart');
});
