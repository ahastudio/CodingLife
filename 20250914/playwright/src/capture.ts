import fs from 'node:fs';
import path from 'node:path';
import { chromium, Page } from 'playwright';

const PAGE_WIDTH_MM = 176;
const PAGE_HEIGHT_MM = 244.8;

async function computePxPerMm(page: Page) {
  return page.evaluate(() => {
    const el = document.createElement('div');
    el.style.width = '100mm';
    el.style.position = 'absolute';
    el.style.left = '-10000mm';
    el.style.top = '0';
    document.body.appendChild(el);
    const pxPerMm = el.getBoundingClientRect().width / 100;
    el.remove();
    return pxPerMm;
  });
}

async function main() {
  const url = 'https://playwright.dev/';

  const browser = await chromium.launch({ headless: true });
  const context = await browser.newContext({ deviceScaleFactor: 2 });
  const page = await context.newPage();

  await page.emulateMedia({ media: 'print' });
  await page.goto(url, { waitUntil: 'networkidle', timeout: 6_0000 });
  await page.waitForTimeout(1_000);

  const pxPerMm = await computePxPerMm(page);
  const width = Math.round(PAGE_WIDTH_MM * pxPerMm);
  const height = Math.round(PAGE_HEIGHT_MM * pxPerMm);

  await page.setViewportSize({ width, height });

  const pageCount = await page.evaluate(() => {
    return Math.ceil(document.body.scrollHeight / window.innerHeight);
  });

  const outDir = 'output';
  fs.mkdirSync(outDir, { recursive: true });

  for (let i = 0; i < pageCount; i++) {
    await page.evaluate((y) => {
      window.scrollTo(0, y);
    }, i * height);
    await page.waitForTimeout(500);

    const filePath = path.join(
      outDir,
      `page-${String(i + 1).padStart(3, '0')}.png`,
    );
    await page.screenshot({
      path: filePath,
      clip: { x: 0, y: 0, width, height },
      fullPage: false,
    });

    console.log('Saved:', filePath);
  }

  await browser.close();

  console.log(`Done. Captured ${pageCount} page(s).`);
}

main();
