import { test, expect } from '@playwright/test';

test('Google', async ({ page }) => {
  await page.goto('https://www.google.com/ncr');

  await expect(page.getByAltText('Google')).toBeVisible();
});
