Feature('Welcome');

Before(({ backdoor, I }) => {
  backdoor.setupDatabase();

  I.login();
});

Scenario('Visit the home page', ({ I }) => {
  I.amOnPage('/');

  I.see('Hello, world!');
});
