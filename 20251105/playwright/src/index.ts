import { readFileSync } from 'node:fs';
import { chromium, Locator } from 'playwright';

type ActionType = 'text' | 'newline';

interface Action {
  type: ActionType;
  value?: string;
}

interface Post {
  title: string;
  actions: Action[];
}

type ActionFn = (element: Locator, ...args: unknown[]) => Promise<void>;

const actionHandlers: Record<ActionType, ActionFn> = {
  newline: (element) => element.press('Enter'),
  text: (element, value: string) =>
    element.pressSequentially(value, { delay: 20 }),
};

const post: Post = {
  title: '테스트 글 제목',
  actions: [
    { type: 'text', value: '테스트 글 본문' },
    { type: 'newline' },
    { type: 'text', value: '줄바꿈까지\n테스트!' },
    { type: 'newline' },
  ],
};

async function main() {
  const [username, password] = readFileSync('account.txt', 'utf-8')
    .split(/\r?\n/)
    .map((line) => line.trim())
    .filter(Boolean);

  const browser = await chromium.launch({
    headless: false,
    // args: ['--start-maximized'],
  });

  const page = await browser.newPage();

  await page.goto('https://nid.naver.com/nidlogin.login');

  await page.fill('input#id', username);
  await page.fill('input#pw', password);

  await page.click('button.btn_login');

  await page.waitForURL('https://www.naver.com/');

  await page.goto('https://blog.naver.com/MyBlog.naver');

  await page.waitForURL(`https://blog.naver.com/${username}`);

  await page.goto(`https://blog.naver.com/${username}/postwrite`);

  const titleElement = page.locator('.se-section-documentTitle');
  await titleElement.waitFor();

  const popupTitle = page.locator('.se-popup-title');
  if (await popupTitle.count()) {
    await page.click('.se-popup-button-cancel');
  }

  const closeButton = page.locator('.se-help-panel-close-button');
  if (await closeButton.count()) {
    await closeButton.click();
  }

  await titleElement.click({ clickCount: 3 });
  await titleElement.pressSequentially(post.title, { delay: 20 });

  const [, bodyElement] = await page.locator('.se-module-text').all();
  await bodyElement.waitFor();
  await bodyElement.click({ clickCount: 3 });

  for (const action of post.actions) {
    await actionHandlers[action.type](bodyElement, action.value);
  }

  await page.locator('[data-click-area="tpb.publish"]').click();

  await page.waitForTimeout(1_000_000);

  // TODO: 발행 옵션 선택

  await page.locator('[data-click-area="tpb*i.publish"]').click();

  await page.waitForURL('https://blog.naver.com/PostView.naver?blogId=*');

  console.log('🎬 Completed!');

  await browser.close();
}

console.log('🚀 Start Automation');
main();
console.log('-'.repeat(30));
