Feature('Create category');

Before(({ backdoor, I }) => {
  backdoor.setupDatabase();

  I.login();
});

Scenario('Create category', ({ I }) => {
  I.amOnPage('/');

  I.click('Categories');

  I.click('카테고리 추가');

  I.fillField('이름', 'NEW-CATEGORY');

  I.click('등록');

  I.see('NEW-CATEGORY');
});
