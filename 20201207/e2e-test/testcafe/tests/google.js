import { Selector } from 'testcafe';

fixture `Google`
  .page `https://www.google.com/ncr`;

test('Search “TestCafe”', async (t) => {
  await t
    .typeText('[name="q"]', 'TestCafe')
    .click('[value="Google Search"]')
    .expect(Selector('#main').innerText).contains('devexpress.github.io');
});
