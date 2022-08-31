Feature('Home page');

Scenario('Visit the home page', ({ I }) => {
  // When
  I.amOnPage('/');

  // Then
  I.see('Hello, world!');
});
