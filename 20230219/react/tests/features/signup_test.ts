Feature('Sign up');

Before(({ backdoor }) => {
  backdoor.setupDatabase();
});

Scenario('Signup success', ({ I }) => {
  I.amOnPage('/');

  I.click('Login');

  I.click('회원 가입');

  I.fillField('E-mail', 'newbie@example.com');
  I.fillField('Name', 'Newbie');
  I.fillField('Password', 'password');
  I.fillField('Password Confirmation', 'password');

  I.click('회원 가입', { css: 'form' });

  I.waitForText('회원 가입이 완료되었습니다');
});

Scenario('Email has been already taken', ({ I }) => {
  I.amOnPage('/');

  I.click('Login');

  I.click('회원 가입');

  I.fillField('E-mail', 'tester@example.com');
  I.fillField('Name', 'Tester');
  I.fillField('Password', 'password');
  I.fillField('Password Confirmation', 'password');

  I.click('회원 가입', { css: 'form' });

  I.waitForText('회원 가입 실패');
});

Scenario('Password confirmation does not match', ({ I }) => {
  I.amOnPage('/');

  I.click('Login');

  I.click('회원 가입');

  I.fillField('E-mail', 'newbie@example.com');
  I.fillField('Name', 'Newbie');
  I.fillField('Password', 'password');
  I.fillField('Password Confirmation', 'xxx');

  I.see('회원 가입', { css: 'button[disabled]' });
});
