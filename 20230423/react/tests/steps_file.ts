const { I } = inject();

export = () => actor({
  login() {
    I.amOnPage('/');
    I.executeScript(() => {
      localStorage.setItem('accessToken', '"ACCESS.TOKEN.0BV000USR0002"');
    });

    I.amOnPage('/');

    // I.fillField('E-mail', 'admin@example.com');
    // I.fillField('Password', 'password');

    // I.click('로그인', { css: 'form' });

    // I.waitForText('Logout');
  },
});
