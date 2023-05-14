Feature('Edit Category');

Before(({ backdoor, I }) => {
  backdoor.setupDatabase();

  I.login();
});

Scenario('Edit category', ({ I }) => {
  I.amOnPage('/categories/0BV000CAT0001/edit');

  I.fillField('이름', 'CHANGED-NAME');

  I.click('수정');

  I.see('CHANGED-NAME');
});
