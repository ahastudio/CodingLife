Feature('Log in');

Before(({ backdoor }) => {
  backdoor.setupDatabase();
});

Scenario('Login success', ({ I }) => {
  I.amOnPage('/');

  I.click('Login');

  I.fillField('E-mail', 'tester@example.com');
  I.fillField('Password', 'password');

  I.click('로그인', { css: 'form' });

  I.waitForText('Cart');
});

Scenario('Login failed', ({ I }) => {
  I.amOnPage('/');

  I.click('Login');

  I.fillField('E-mail', 'tester@example.com');
  I.fillField('Password', 'xxx');

  I.click('로그인', { css: 'form' });

  I.waitForText('로그인 실패');
});
