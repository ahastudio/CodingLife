const { I } = inject();

export = () => actor({
  login() {
    I.amOnPage('/');

    I.click('Login');

    I.fillField('E-mail', 'tester@example.com');
    I.fillField('Password', 'password');

    I.click('로그인', { css: 'form' });

    I.waitForText('Cart');
  },
});
