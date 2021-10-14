Feature('welcome');

Scenario('Visit the home page', ({ I }) => {
  I.amOnPage('/');

  I.see('Hello, world!');
});

Scenario('Add a new post', ({ I }) => {
  I.amOnPage('/');

  I.dontSee('What time is it?');

  I.click('Add post!');

  I.waitForText('What time is it?');
});
